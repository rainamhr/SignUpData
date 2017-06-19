package com.study.signupdata;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
//import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.study.signupdata.database.AddResult;
import com.study.signupdata.retrofit.ApiClient;
import com.study.signupdata.retrofit.ApiInterface;
import com.study.signupdata.stickyHeader.ItemHeader;
import com.study.signupdata.stickyHeader.ItemHeaderViewBinder;
import com.study.signupdata.stickyHeader.MemberItemViewBinder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tellh.com.stickyheaderview_rv.adapter.DataBean;
import tellh.com.stickyheaderview_rv.adapter.StickyHeaderViewAdapter;

import static android.support.v7.widget.RecyclerView.*;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private LayoutManager layoutManager;
    private Button signOutButton;
    //private SwipeRefreshLayout mswipeRefreshLayout;

    private FloatingActionButton floatingActionButton;
    Button save;
    dbHelper dbHelper;

    private ApiInterface apiInterface;
    String id, name, address, email;
    String mail;
    TextView loginEmail;
    SQLiteDatabase sqLiteDatabase;

    Context context;

    private RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    List<Member> list;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
/*
        initView();
        stickyView();
        add_gar();*/

        Intent intent = getIntent();
        mail = intent.getStringExtra("email").toString();
        Log.d("mail", mail);
        loginEmail = (TextView) findViewById(R.id.mail);
        loginEmail.setText(mail);


        signOutButton = (Button) findViewById(R.id.btn_sign_out);

        //mswipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        //mswipeRefreshLayout.setOnRefreshListener(this);

        list = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerAdapter = new RecyclerAdapter(getApplicationContext(), list);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(recyclerAdapter);

        dbHelper = new dbHelper(this);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(SAVECLICK);
        //swipeRefresh();
        get();
        //post();
    }

    private View.OnClickListener SAVECLICK = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.e("floatingButton", "entered");
            dialog = new Dialog(Main2Activity.this);
            dialog.setContentView(R.layout.add_member_dialogue);
            dialog.setTitle("ADD MEMBER");

            save = (Button) dialog.findViewById(R.id.save);

            try {
                save.setOnClickListener(SAVE);
            } catch (Exception e) {
                Log.e("ERROR...", e.getMessage());

            }
            dialog.show();
        }
    };


    private View.OnClickListener SAVE = new OnClickListener() {
        @Override
        public void onClick(View v) {
            final EditText number = (EditText) dialog.findViewById(R.id.number);
            final EditText name = (EditText) dialog.findViewById(R.id.name);
            final EditText address = (EditText) dialog.findViewById(R.id.address);

            String STU_id = number.getText().toString();
            String STU_name = name.getText().toString();
            String STU_address = address.getText().toString();

            final Member member = new Member(STU_name, STU_address);
            long id = dbHelper.insertMembers(member);
            Log.d("id", String.valueOf(id));

            member.setId(String.valueOf(id));
            member.setAdded_by(mail);
            apiInterface = ApiClient.getAPIclient().create(ApiInterface.class);
            Call<AddResult> call = apiInterface.insertMember(member.getId(), member.getName(), member.getAddress(), member.getAdded_by());
            call.enqueue(new Callback<AddResult>() {
                @Override
                public void onResponse(Call<AddResult> call, Response<AddResult> response) {
                    AddResult result = response.body();
                    Log.d("reponse", result.status);
                    Log.d("reponse", result.message);
                    Log.d("reponse", response.body().toString());

                    list.add(member);
                    recyclerAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<AddResult> call, Throwable t) {
                    Log.d("onfailure", t.toString());
                }
            });
            dialog.dismiss();
        }
    };

    public void get() {

        apiInterface = ApiClient.getAPIclient().create(ApiInterface.class);
        Call<MemberResult> call1 = apiInterface.getList();

        call1.enqueue(new Callback<MemberResult>() {
            @Override
            public void onResponse(Call<MemberResult> call, Response<MemberResult> response) {
                MemberResult memberResult = response.body();
                Log.d("response", memberResult.status);
                List<Member> members = memberResult.data;

                Log.d("response",memberResult.toString());

                for (int i = 0; i < members.size(); i++) {
                    Member item = members.get(i);
                    list.add(item);
                }

                Log.d("adapter", String.valueOf(recyclerAdapter.getItemCount()));
                recyclerAdapter.notifyDataSetChanged();
                //Log.d("retrofitttt", response+"");
                Log.d("onResponse", "" + response.code() +
                        "  response body " + response.body() +
                        " responseError " + response.errorBody() + " responseMessage " +
                        response.message());


            }

            @Override
            public void onFailure(Call<MemberResult> call, Throwable t) {
                Log.d("onfailure", t.toString());
            }
        });

        signOutButton.setOnClickListener(this);

    }

    public void post() {

        apiInterface = ApiClient.getAPIclient().create(ApiInterface.class);

        final Member member = new Member();

        member.setId(id);
        member.setName(name);
        member.setAddress(address);
        member.setEmail(email);

        Call<Member> call = apiInterface.insertInfo(member.getId(), member.getName(), member.getAddress(), member.getEmail());

        call.enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                Log.d("onResponse", "" + response.code() +
                        "  response body " + response.body() +
                        " responseError " + response.errorBody() + " responseMessage " +
                        response.message());

                Member info = response.body();

                Log.d("onResponse", info.getId() + info.getName() + info.getAddress() + info.getEmail());
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                Log.d("onfailure", t.toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Log.e("clicked", "String" + v.getId());
        switch (id) {
            case R.id.btn_sign_out:
                signOut();
        }
    }

    public void signOut() {
        Log.d("signout", "home");
        Intent logout = new Intent(Main2Activity.this, MainActivity.class);
        logout.putExtra("logout1", true);
        startActivity(logout);
        finish();
    }

   /* public int updateMembers(Member member) {

        sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConstants.ID, member.getId());
        contentValues.put(DbConstants.NAME, member.getName());
        contentValues.put(DbConstants.ADDRESS, member.getAddress());
        int result = sqLiteDatabase.update(DbConstants.MEMBER_TABLE, contentValues, DbConstants.ID + " =? ", new String[]{member.getId()});

        return result;
    }

    public void deleteMembers(Member member) {

        sqLiteDatabase = dbHelper.getReadableDatabase();
        Log.d("deleted : ", member.getId());
        sqLiteDatabase.delete(DbConstants.MEMBER_TABLE, DbConstants.ID + "=?", new String[]{member.getId()});

    }*/
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation");
        builder.setMessage("Are you sure want to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                signOut();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();

    }

    /*@Override
    public void onRefresh() {

        swipeRefresh();
        get();
        post();

        Toast.makeText(this, "Refresh", Toast.LENGTH_LONG).show();
        recyclerView.postDelayed(new Runnable() {


            @Override
            public void run() {
                mswipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    public void swipeRefresh() {

        recyclerAdapter = new RecyclerAdapter(getApplicationContext(), dbHelper.getStudentInfo());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setHasFixedSize(true);
    }*/

/* public void stickyView(){
        memberList = new ArrayList<>();
        adapter = new StickyHeaderViewAdapter(memberList)
                .RegisterItemType(new MemberItemViewBinder())
                .RegisterItemType(new ItemHeaderViewBinder());
        rv.setAdapter(adapter);
    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
    }

    private void add_gar(){
        memberList.add(new ItemHeader("ID"));
        memberList.add(new ItemHeader("Name"));
        memberList.add(new ItemHeader("Address"));
    }*/
}
