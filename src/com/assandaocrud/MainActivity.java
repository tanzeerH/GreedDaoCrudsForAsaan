package com.assandaocrud;

import java.util.List;

import com.asaandaocrud.R;

import de.greenrobot.dao.query.WhereCondition;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import asaan.dao.AddItem;
import asaan.dao.AddItemDao;
import asaan.dao.DaoMaster;
import asaan.dao.DaoMaster.OpenHelper;
import asaan.dao.DaoSession;
import asaan.dao.ModItem;
import asaan.dao.ModItemDao;

public class MainActivity extends Activity{
	
	 private SQLiteDatabase db;
	private DaoMaster daoMaster;
	private DaoSession daoSession;
	private AddItemDao addItemDao;
	private AddItem addItem;
	private ModItem modItem;
	private ModItemDao modItemDao;
	private ListView lv;
	private ListAdapter adapter;
	List<AddItem> list;
	private Button insertBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv=(ListView)findViewById(R.id.lv_1);
		insertBtn=(Button)findViewById(R.id.btn);
		insertBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				inserData();
				addItemDao = daoSession.getAddItemDao();
				list=addItemDao.queryBuilder().list();
				Log.e("LIST",""+list.size());
				adapter.notifyDataSetChanged();	
			}
		});
		
		OpenHelper helper = new DaoMaster.DevOpenHelper(this, "asaan-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        addItemDao = daoSession.getAddItemDao();
		modItemDao=daoSession.getModItemDao();
		list=addItemDao.queryBuilder().list();
		adapter=new ListAdapter(getApplicationContext(),R.layout.lv_row,list);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
			}
		});
		//UpdateData();
		deleteData();
	}
	private void inserData()
	{
		List<AddItem> l=addItemDao.queryBuilder().list();
		addItem=new AddItem((long)l.size()+1,2,1001,"abcd"+l.size());
		addItemDao.insert(addItem);
		
		modItem=new ModItem(l.size()+1,1);
		modItemDao.insert(modItem);
				
		
	}
	private void UpdateData()
	{
	
		String q="UPDATE "+ AddItemDao.TABLENAME + " SET quantity=100 WHERE _id<2";
		daoMaster.getDatabase().execSQL(q);
		
				
		
	}
	private void deleteData()
	{
		String q="DELETE FROM "+ AddItemDao.TABLENAME + " WHERE _id<2";
		daoMaster.getDatabase().execSQL(q);
	}

}
