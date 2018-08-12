package com.bwei.erzhoukaomoni.ui.activity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.erzhoukaomoni.R;
import com.bwei.erzhoukaomoni.data.bean.News;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Jack Lee on 2018/8/12.
 * name:Juck Lee
 */

class Myadapter extends BaseAdapter{
    private List<News.DataBean> list;
    private Context context;

    public Myadapter(List<News.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder  viewholder = null;
        if (convertView==null){
            viewholder  = new Viewholder();
            convertView = View.inflate(context, R.layout.layout_list, null);
            viewholder.image_view= convertView.findViewById(R.id.image_view);
            viewholder.text_title= convertView.findViewById(R.id.text_title);
            viewholder.text_price= convertView.findViewById(R.id.text_price);

            convertView.setTag(viewholder);
        }else{
            viewholder = (Viewholder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(list.get(position).getImages(),viewholder.image_view);
        viewholder.text_title.setText(list.get(position).getTitle());
        viewholder.text_price.setText(list.get(position).getPrice()+"");
        return convertView;
    }
    public class  Viewholder{
        ImageView image_view;
        TextView text_title;
        TextView  text_price;

    }
}
