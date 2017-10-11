package com.beikbank.android.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.beikbank.android.adapter.HongbaoAdapter;
import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data.DingqiPI;
import com.beikbank.android.data.Hongbao;
import com.beikbank.android.data.Hongbao_data;
import com.beikbank.android.dataparam.HongbaoParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.hongbao.HongbaoUtil;
import com.beikbank.android.utils.hongbao.HongbaoUtil2;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.webwiew.WebWiewInface;

import comc.beikbank.android.R;

/**历史红包
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-6-11
 */

public class YouHuiQuanActivity3 extends BaseActivity1 implements View.OnClickListener
{
    
    Activity act;
    private TextView right,titleTv;
    private ScrollView sl;
    /**
     * 跳转到历史优惠券
     */
    TextView tv1;
    /**
     * 跳转到历史优惠券
     */
    TextView tv2;
    //
    private RelativeLayout rl;
    /**
     * 父布局
     */
    LinearLayout ll;
    /**
     * 没有过期需要显示文本
     */
    LinearLayout ll2;
    /**
     * 过期需要显示文本
     */
    LinearLayout ll3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youhuiquan);
        StateBarColor.init(this,0xffffffff);
        initView();
        act=this;
        //initData();
    }
    private void initView()
    {
        
        LinearLayout left=(LinearLayout) findViewById(R.id.linear_left);
        left.setOnClickListener(this);
     //   lv=(ListView) findViewById(R.id.lv);
        titleTv = (TextView) findViewById(R.id.titleTv);
        titleTv.setText("历史红包");
        tv1=(TextView) findViewById(R.id.tv1);
        tv2=(TextView) findViewById(R.id.tv2);
    	//right=(TextView) findViewById(R.id.right);
		//right.setVisibility(View.VISIBLE);
		//right.setText("使用说明");
		
		//right.setOnClickListener(this);
		
		sl=(ScrollView) findViewById(R.id.sv);
		rl=(RelativeLayout) findViewById(R.id.rl);
		tv1.setOnClickListener(this);
		tv2.setOnClickListener(this);
        ll=(LinearLayout) findViewById(R.id.ll);
        ll2=(LinearLayout) findViewById(R.id.ll2);
        ll3=(LinearLayout) findViewById(R.id.ll3);
        ll2.setVisibility(View.GONE);
        
        ll3.setVisibility(View.VISIBLE);
        LinearLayout ll_parent=(LinearLayout) findViewById(R.id.ll_parent);
        ll_parent.setVisibility(View.GONE);
        ScrollView sv=(ScrollView) findViewById(R.id.sv);
        sv.setVisibility(View.VISIBLE);
        tv1.setVisibility(View.GONE);
        
    }
	private void initData()
	{   
		HongbaoParam hp=new HongbaoParam();
		hp.userId=BeikBankApplication.getUserid();
		//hp.userId="59";
		TongYongManager tym=new TongYongManager(this, icb,hp);
		tym.start();
	}
    ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{
				Hongbao_data hd=(Hongbao_data) obj;
				ArrayList<Hongbao> list=hd.data;
			   if(list!=null&&list.size()>0)
			   {
				 //HongbaoAdapter ha=new HongbaoAdapter(act,list,null,true);
				 //lv.setAdapter(ha);
				 list=HongbaoUtil.select7(list);
				 HongbaoUtil2 hu2=new HongbaoUtil2(act);
				 Collections.sort(list);
				 hu2.addView(ll, list);
				 ll3.setVisibility(View.VISIBLE);
			   }
			   else
			   {
//				   sl.setVisibility(View.GONE);
//				   rl.setVisibility(View.VISIBLE);
			   }
			}
			else
			{
				//sl.setVisibility(View.GONE);
				//rl.setVisibility(View.VISIBLE);
				
			}
			 ll3.setVisibility(View.VISIBLE);
			
		}
	};
   public void onClick(View view)
   {
      switch (view.getId())
      {
         case R.id.linear_left:
             finish();
          break;
         case R.id.right:
             startAct(YouHuiQuanActivity2.class);
          break;
         case R.id.tv1:
             startAct(YouHuiQuanActivity2.class);
             break;
         case R.id.tv2:
             startAct(YouHuiQuanActivity2.class);
             break;    
      }
      
   }
   private void startAct(Class cla)
   {
	   Intent intent=new Intent(this, cla);
	   startActivity(intent);
   }
}