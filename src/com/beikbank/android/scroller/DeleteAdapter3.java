package com.beikbank.android.scroller;

import java.util.List;

import com.beikbank.android.activity.MessageActivity;
import com.beikbank.android.data.Message;
import com.beikbank.android.dataparam.MessageReadParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.MessagedelManager;
import coma.beikbank.android.R;



import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 
 * @author Administrator
 *新的消息通知  v2
 */
public class DeleteAdapter3 extends BaseAdapter {

	public static ListItemDelete itemDelete = null;
	//private List<String> listDatas;
	private List<Message> list;
	private LayoutInflater mInflater;
	private Activity context;
	/**
	 * 1通知2消息
	 */
    int index;
	public DeleteAdapter3(Activity context, List<Message> list,int index) {
		mInflater = LayoutInflater.from(context);
		this.list= list;
		this.context = context;
		this.index=index;
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		Message msg=list.get(position);
		//初始化控件
		if (convertView == null) {
			holder = new ViewHolder();
			LinearLayout ll=new LinearLayout(context);
			LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
			
			if(index==1)
			{
				convertView = mInflater.inflate(R.layout.item_delete2,ll,false);
			}
			else
			{
				convertView = mInflater.inflate(R.layout.item_delete,ll,false);
			}
			convertView.setLayoutParams(lp);
			holder.iv=(ImageView) convertView.findViewById(R.id.iv_iv1);
			//holder.iv=(ImageView) convertView.findViewById(R.id.iv_yidu);
			holder.title = (TextView) convertView
					.findViewById(R.id.title);
			holder.context = (TextView) convertView
					.findViewById(R.id.context);
			holder.time = (TextView) convertView.findViewById(R.id.time);
			
			holder.ll_del=(LinearLayout) convertView.findViewById(R.id.ll_del);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		//设置控件的属性和值
		if("0".equals(msg.readFlg))
		{
			 holder.iv.setImageResource(R.drawable.left_message);
			 holder.title.setTextColor(0xff000000);
			 holder.time.setTextColor(0xff747474);
			//holder.iv.setVisibility(View.VISIBLE);
		}
		else
		{
			 holder.iv.setImageResource(R.drawable.message_has);
			 holder.title.setTextColor(0xff747474);
			 holder.time.setTextColor(0xff747474);
			//holder.iv.setVisibility(View.INVISIBLE);
		}
		holder.title.setText(msg.tittle);
		holder.context.setText(msg.sender);
		holder.time.setText(msg.sendTime);
		if(index==1)
		{
			holder.ll_del.setVisibility(View.GONE);
			convertView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//((MessageActivity)context).onItemClick(position);
					
				}
			});
		}
		else
		{
			
		
		holder.ll_del.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 MessageReadParam mrp=new MessageReadParam();
		    	 mrp.id=list.get(position).id;
		  		// mrp.userId=BeikBankApplication.getUserid();
				 MessagedelManager mdm=new MessagedelManager(context,new ICallBack() {
					
					@Override
					public void back(Object obj) {
						if(obj!=null)
						{
						  list.remove(position);
					      notifyDataSetChanged();
						  ItemDeleteReset();
						}
					}
				},mrp);
				 mdm.start();
			}
		});
		}
		
		return convertView;
	}

	class ViewHolder {
		ImageView iv;
		TextView title;
		TextView context;
		TextView time;
		LinearLayout ll_del;
	}

	private Toast mToast;

	public void showInfo(String text) {
		if (mToast == null) 
		{
			mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(text);
			mToast.setDuration(Toast.LENGTH_SHORT);
		}
		mToast.show();
	}

	public static void ItemDeleteReset() {
		if (itemDelete != null) {
			itemDelete.reSet();
		}
	}
}
