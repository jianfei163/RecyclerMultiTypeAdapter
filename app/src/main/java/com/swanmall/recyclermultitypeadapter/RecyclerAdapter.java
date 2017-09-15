package com.swanmall.recyclermultitypeadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by asus on 2017/9/15.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<String> list;

    private final int IMAGE_HEAD = 0;
    private final int TEXT_MID = 1;
    private final int LIST_IMAGE_BOTTON = 2;

    public RecyclerAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case IMAGE_HEAD:
                view = LayoutInflater.from(context).inflate(R.layout.item_tuan_list_msg_act_head_imag, parent, false);
                return new TuanListMsgAdapterHeadImageHolder(view);
            case TEXT_MID:
                view = LayoutInflater.from(context).inflate(R.layout.item_tuan_list_msg_act_mid_text, parent, false);
                return new TuanListMsgAdapterMidHolder(view);
            case LIST_IMAGE_BOTTON:
                view = LayoutInflater.from(context).inflate(R.layout.item_tuan_list_msg_act_botton_list, parent, false);
                return new TuanListMsgAdapterBottonList(view);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof TuanListMsgAdapterHeadImageHolder) {
            TuanListMsgAdapterHeadImageHolder headImageHolder = (TuanListMsgAdapterHeadImageHolder) holder;
            headImageHolder.head_imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickListener(position);
                }
            });
        } else if (holder instanceof TuanListMsgAdapterMidHolder) {
            TuanListMsgAdapterMidHolder midHolder = (TuanListMsgAdapterMidHolder) holder;
        } else if (holder instanceof TuanListMsgAdapterBottonList) {
            TuanListMsgAdapterBottonList bottonListHolder = (TuanListMsgAdapterBottonList) holder;
            bottonListHolder.tv_content_info.setText(list.get(position));
            bottonListHolder.mid_imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickListener(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public int getSpanSize(int position) {
        int type = getItemViewType(position);
        switch (type) {
            case IMAGE_HEAD:
                return 2;
            case TEXT_MID:
                return 2;
            case LIST_IMAGE_BOTTON:
                return 1;
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return IMAGE_HEAD;
        } else if (position == 1) {
            return TEXT_MID;
        } else {
            return LIST_IMAGE_BOTTON;
        }
    }

    class TuanListMsgAdapterBottonList extends RecyclerView.ViewHolder {
        TextView tv_content_info;
        ImageView mid_imageview;

        public TuanListMsgAdapterBottonList(View itemView) {
            super(itemView);
            tv_content_info = (TextView) itemView.findViewById(R.id.tuan_gridview_textview_content_info);
            mid_imageview = (ImageView) itemView.findViewById(R.id.item_tuan_list_msg_act_mid_image);
        }
    }

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClickListener(int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private class TuanListMsgAdapterHeadImageHolder extends RecyclerView.ViewHolder {
        ImageView head_imageview;

        public TuanListMsgAdapterHeadImageHolder(View view) {
            super(view);
            head_imageview = (ImageView) view.findViewById(R.id.item_tuan_list_msg_act_head_image_image);
        }
    }

    private class TuanListMsgAdapterMidHolder extends RecyclerView.ViewHolder {
        public TuanListMsgAdapterMidHolder(View view) {
            super(view);
        }
    }
}

