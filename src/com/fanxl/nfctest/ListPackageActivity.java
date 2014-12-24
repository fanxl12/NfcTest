package com.fanxl.nfctest;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class ListPackageActivity extends ListActivity implements OnItemClickListener {

	private List<String> packages = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		PackageManager pm = getPackageManager();
		List<PackageInfo> packageInfos = pm
				.getInstalledPackages(PackageManager.GET_ACTIVITIES);
		for (PackageInfo packageInfo : packageInfos) {
			packages.add(packageInfo.applicationInfo.loadLabel(pm) + "\n"
					+ packageInfo.packageName);
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, packages);
		setListAdapter(adapter);
		getListView().setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent();
		intent.putExtra("package_name", packages.get(position));
		setResult(1, intent);
		finish();
	}

	
}
