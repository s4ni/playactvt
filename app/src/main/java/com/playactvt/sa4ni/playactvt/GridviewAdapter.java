package com.playactvt.sa4ni.playactvt;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridviewAdapter extends BaseAdapter
{
	private ArrayList<String> lstname;
	private ArrayList<String> lstlbl;
	private ArrayList<Drawable> lsticn;
	private Activity activity;
	
	public GridviewAdapter(Activity activity,ArrayList<String> listName, ArrayList<String> listLbl, ArrayList<Drawable> listIcn) {
		super();
		this.lstname = listName;
		this.lstlbl = listLbl;
		this.lsticn = listIcn;
		this.activity = activity;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lstname.size();
	}

	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return lstname.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static class ViewHolder
	{
		public ImageView imgActvticn;
		public TextView txtActvtname;
		public TextView txtActvtlbl;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder view;
		LayoutInflater inflator = activity.getLayoutInflater();
		
		if(convertView==null)
		{
			view = new ViewHolder();
			convertView = inflator.inflate(R.layout.lstbase, null);

			view.txtActvtname=(TextView)convertView.findViewById(R.id.tV_PkgNm);
			view.txtActvtlbl=(TextView)convertView.findViewById(R.id.tV_PkgLbl);
			view.imgActvticn=(ImageView)convertView.findViewById(R.id.iV_PkgIcn);
			
			convertView.setTag(view);
		}
		else
		{
			view = (ViewHolder) convertView.getTag();
		}

		view.txtActvtname.setText(lstname.get(position));
		view.txtActvtlbl.setText(lstlbl.get(position));
		view.imgActvticn.setImageDrawable(lsticn.get(position));
		
		return convertView;
	}

}
