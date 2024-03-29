package com.example.chickenrun.gameRoom;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.chickenrun.gameRoom.Activity_Waiting_Room.GET_HEIGHT_USER_SPACE;
import static com.example.chickenrun.gameRoom.Activity_Waiting_Room.HANDLER_READY_CLICK;

import com.example.chickenrun.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.chickenrun.Lobby.Activity_Lobby.GET_MY_NAME;

public class adapterWaitingRoom extends RecyclerView.Adapter<adapterWaitingRoom.ViewHolder>
{

    private Context mContext;
//    public static List<item_participant_user> itemParticipant = new ArrayList<>();
    public static List<item_participant_user> itemParticipant;

    String TAG = "adapterWaitingRoom";

    public adapterWaitingRoom(Context context, List<item_participant_user> item_participant_users)
    {
        mContext = context;
        itemParticipant = item_participant_users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // 아이템 레이아웃 연결
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_waiting_user, parent, false);

        return new ViewHolder(view);
    }

    String readyReceive[];

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position)
    {
        // todo: 화면 비율 구하기
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) holder.player_image.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;  // 핸드폰의 가로 해상도를 구함.
//            int deviceHeight = displayMetrics.heightPixels;  // 핸드폰의 세로 해상도를 구함.
//        Log.e(TAG, "onBindViewHolder: deviceWidth    : " + deviceWidth);

        deviceWidth = deviceWidth / 2;
//        Log.e(TAG, "onBindViewHolder: deviceWidth / 2: " + deviceWidth);

        int deviceHeight = (int) ((float) deviceWidth * 0.94);  // 세로의 길이를 가로의 길이의 1배로 or 1.5 = 1.5배로
//            int deviceHeight = deviceWidth * 1.1;  // 세로의 길이를 가로의 길이의 1배로 or 1.5 = 1.5배로

        holder.view_size.getLayoutParams().width = deviceWidth;  // 아이템 뷰의 세로 길이를 구한 길이로 변경
        holder.view_size.getLayoutParams().height = GET_HEIGHT_USER_SPACE / 2;  // 아이템 뷰의 세로 길이를 구한 길이로 변경
        holder.view_size.requestLayout(); // 변경 사항 적용
        Log.e(TAG, "onBindViewHolder: GET_HEIGHT_USER_SPACE: " + GET_HEIGHT_USER_SPACE / 2 );

        /**
         이렇게 하면 동적으로 원하는 크기의 아이템 항목을 구성할 수 있고,
         재사용 시 스크롤도 자연스럽게 이동한다.
         (다만 조금 느린 감이 있다.)

         매번 디스 플레이의 값을 구하지 않고,
         메인에서 한 번만 호출하여 등록해도 된다.

         출처: https://flymogi.tistory.com/entry/안드로이드-리사이클러-뷰-그리드-레이아웃-아이템-세로-동적-비율 [하늘을 난 모기]
         */

        if (itemParticipant.get(position).getUserName().equals("hoonkim1"))
        {
            holder.player_image.setImageResource(itemParticipant.get(position).getUserIcon()[0]);
        }

        else if (itemParticipant.get(position).getUserName().equals("chickenman"))
        {
            holder.player_image.setImageResource(itemParticipant.get(position).getUserIcon()[1]);
        }

        else if (itemParticipant.get(position).getUserName().equals("chickengirl"))
        {
            holder.player_image.setImageResource(itemParticipant.get(position).getUserIcon()[2]);
        }

        else if (itemParticipant.get(position).getUserName().equals("chickenhero"))
        {
            holder.player_image.setImageResource(itemParticipant.get(position).getUserIcon()[3]);
        }


//        Log.e(TAG, "onBindViewHolder: itemParticipant.get(position).getUserIcon()[0]: " + itemParticipant.get(position).getUserIcon()[0] );
//        Log.e(TAG, "onBindViewHolder: itemParticipant.get(position).getUserIcon()[1]: " + itemParticipant.get(position).getUserIcon()[1] );
//        Log.e(TAG, "onBindViewHolder: itemParticipant.get(position).getUserIcon()[2]: " + itemParticipant.get(position).getUserIcon()[2] );
//        Log.e(TAG, "onBindViewHolder: itemParticipant.get(position).getUserIcon()[3]: " + itemParticipant.get(position).getUserIcon()[3] );

//        if (itemParticipant.get(i).getUserName().equals("hoonkim1"))
//        {
//            holder.player_image.setImageResource(R.drawable.ic_person_pink);
//        }
//
//        else if (itemParticipant.get(i).getUserName().equals("chickenman"))
//        {
//            holder.player_image.setImageResource(R.drawable.ic_person_black);
//        }
//
//        else if (itemParticipant.get(i).getUserName().equals("chickengirl"))
//        {
//            holder.player_image.setImageResource(R.drawable.ic_person_blue);
//        }
//
//        else if (itemParticipant.get(i).getUserName().equals("chickenhero"))
//        {
//            holder.player_image.setImageResource(R.drawable.ic_person_gray);
//        }

//        holder.player_image.setImageResource(Integer.parseInt(itemParticipant.get(position).getIcon()));

//        Log.e(TAG, "onBindViewHolder: itemParticipant.get(position).getIcon(): " + itemParticipant.get(position).getIcon() );

        // 참가자 닉네임
        holder.join_user_name.setText(itemParticipant.get(position).getUserName());

        // 방장 표시
        if (itemParticipant.get(position).isHost)
        {
            holder.host_mark.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.host_mark.setVisibility(View.GONE);
        }

        // 레디 표시
        if (itemParticipant.get(position).isReady)
        {
            holder.button_ready.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.button_ready.setVisibility(View.GONE);
        }


        // 레디 클릭 알림 수신 대기 (핸들러)
/*        HANDLER_READY_CLICK = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                if (msg.what == 1112)
                {
                    String receive = msg.obj.toString();
                    Log.e(TAG, "handleMessage: receive: " + receive);

                    readyReceive = receive.split(", ");

                    Log.e(TAG, "handleMessage: readyReceive[0]: " + readyReceive[0] );
                    Log.e(TAG, "handleMessage: readyReceive[1]: " + readyReceive[1] );

                    Log.e(TAG, "handleMessage: itemParticipant.size(): " + itemParticipant.size() );

                    for (int i = 0; i < itemParticipant.size(); i++)
                    {
                        Log.e(TAG, "handleMessage: itemParticipant.get(position).getUserName(): " + itemParticipant.get(position).getUserName() );
                        if (itemParticipant.get(i).getUserName().equals(readyReceive[1]))
                        {
                            Log.e(TAG, "handleMessage: 도달함: itemParticipant.get(position).getUserName().equals(GET_MY_NAME)" );

                            if (readyReceive[0].equals("ready_off_recycler_view"))
                            {
                                Log.e(TAG, "handleMessage: " + readyReceive[1] + "유저 ready Off");
                                holder.button_ready.setVisibility(View.GONE);
                            }

                            else*//* if (readyReceive[0].equals("ready_on_recycler_view"))*//*
                            {
                                Log.e(TAG, "handleMessage: " + readyReceive[1] + "유저 ready On");
                                holder.button_ready.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }
            }
        };*/

        // 준비 버튼 클릭
    }

    @Override
    public int getItemCount()
    {
        return itemParticipant.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public View view;
        public TextView join_user_name;
        public TextView host_mark;
        public TextView button_ready;
        public ImageView player_image;
        public LinearLayout view_size;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            view = itemView;

            join_user_name = itemView.findViewById(R.id.join_user_name);
            host_mark = itemView.findViewById(R.id.host_mark);
            button_ready = itemView.findViewById(R.id.button_ready);
            player_image = itemView.findViewById(R.id.player_image);
            view_size = itemView.findViewById(R.id.view_size);
        }
    }
}
