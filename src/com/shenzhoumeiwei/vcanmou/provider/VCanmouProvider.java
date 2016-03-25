package com.shenzhoumeiwei.vcanmou.provider;

import java.util.HashMap;
import java.util.List;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;


/**
 * 这里使用ContentProvider，是为了为数据的访问提供一个统一的接口，方便客户端的操作，同时可以使用CursorLoader进行数据的加载
 */
public class VCanmouProvider extends ContentProvider {

    private static final String TAG = "EmenuClerkReducedProvider";

    private VCanmouDBHelper mDatabaseHelper;

    /**
     * 表的MIME类型
     */
    private static final String VENDOR_TYPE_DIR = "vnd.android.cursor.dir";
    private static final String VENDOR_TYPE_ITEM = "vnd.android.cursor.item";
    private static final String VENDOR_SPECIFIC = "vnd." + VCanmouContract.AUTHORITY
            + ".";

    /**
     * 各Uri通过UriMatcher匹配后的返回结果
     */
    /**
     * 菜品
     */
    private static final int CARTE = 1;
    private static final int CARTE_ID = 2;


    private static final UriMatcher mUriMatcher;

    private static HashMap<String, String> mDishMap; // 菜品视图的投影字段
    private static HashMap<String, String> mCarteTypeMap; // 菜品类别的投影字段
    private static HashMap<String, String> mComboCarteMap; // 套餐菜品的投影字段
    private static HashMap<String, String> mComboGroupMap; // 套餐分组的投影字段
    private static HashMap<String, String> mTableTypeMap; // 桌台类别的投影字段
    private static HashMap<String, String> mTableInfoMap; // 桌台信息的投影字段
    private static HashMap<String, String> mMakeMethodMap; // 制作方法的投影字段
    private static HashMap<String, String> mMakeTasteMap; // 制作口味的投影字段
    private static HashMap<String, String> mDynamicInfoMap; // 动态信息的投影字段

    /**
     * 该静态代码块用来将各Uri装入UriMatcher并初始化要查询表的投影投影字段
     */
    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        /**
         * 菜品
         */
        mUriMatcher.addURI(VCanmouContract.AUTHORITY,
                VCanmouContract.Carte.TABLE_NAME, CARTE);
        mUriMatcher.addURI(VCanmouContract.AUTHORITY,
                VCanmouContract.Carte.TABLE_NAME + "/#", CARTE_ID);


        /**
         * 菜品类别的投影字段
         */
        mCarteTypeMap = new HashMap<String, String>();
        mCarteTypeMap.put(VCanmouContract.CarteType.CT_ID,
                VCanmouContract.CarteType.CT_ID);
        mCarteTypeMap.put(VCanmouContract.CarteType.CT_PARENT_ID,
                VCanmouContract.CarteType.CT_PARENT_ID);
        mCarteTypeMap.put(VCanmouContract.CarteType.CT_CODE,
                VCanmouContract.CarteType.CT_CODE);
        mCarteTypeMap.put(VCanmouContract.CarteType.CT_NAME,
                VCanmouContract.CarteType.CT_NAME);

        /**
         * 套餐菜品的投影字段
         */
        mComboCarteMap = new HashMap<String, String>();
        mComboCarteMap.put(VCanmouContract.ComboCarte.CCAR_ID,
                VCanmouContract.ComboCarte.CCAR_ID);
        mComboCarteMap.put(VCanmouContract.ComboCarte.CS_ID,
                VCanmouContract.ComboCarte.CS_ID);
        mComboCarteMap.put(VCanmouContract.ComboCarte.CG_ID,
                VCanmouContract.ComboCarte.CG_ID);
        mComboCarteMap.put(VCanmouContract.ComboCarte.CCAR_MIN,
                VCanmouContract.ComboCarte.CCAR_MIN);
        mComboCarteMap.put(VCanmouContract.ComboCarte.CCAR_MAX,
                VCanmouContract.ComboCarte.CCAR_MAX);
        mComboCarteMap.put(VCanmouContract.ComboCarte.CCAR_PRICE,
                VCanmouContract.ComboCarte.CCAR_PRICE);

        /**
         * 套餐分组的投影字段
         */
        mComboGroupMap = new HashMap<String, String>();
        mComboGroupMap.put(VCanmouContract.ComboGroup.CG_ID,
                VCanmouContract.ComboGroup.CG_ID);
        mComboGroupMap.put(VCanmouContract.ComboGroup.CS_ID,
                VCanmouContract.ComboGroup.CS_ID);
        mComboGroupMap.put(VCanmouContract.ComboGroup.CG_COUNT,
                VCanmouContract.ComboGroup.CG_COUNT);
        mComboGroupMap.put(VCanmouContract.ComboGroup.CG_NAME,
                VCanmouContract.ComboGroup.CG_NAME);

        /**
         * 桌台类别的投影字段
         */
        mTableTypeMap = new HashMap<String, String>();
        mTableTypeMap.put(VCanmouContract.TableType.TT_ID,
                VCanmouContract.TableType.TT_ID);
        mTableTypeMap.put(VCanmouContract.TableType.TT_CODE,
                VCanmouContract.TableType.TT_CODE);
        mTableTypeMap.put(VCanmouContract.TableType.TT_NAME,
                VCanmouContract.TableType.TT_NAME);

        /**
         * 桌台信息的投影字段
         */
        mTableInfoMap = new HashMap<String, String>();
        mTableInfoMap.put(VCanmouContract.TableInfo.TI_ID,
                VCanmouContract.TableInfo.TI_ID);
        mTableInfoMap.put(VCanmouContract.TableInfo.TT_ID,
                VCanmouContract.TableInfo.TT_ID);
        mTableInfoMap.put(VCanmouContract.TableInfo.TI_SEAT_COUNT,
                VCanmouContract.TableInfo.TI_SEAT_COUNT);
        mTableInfoMap.put(VCanmouContract.TableInfo.TI_STATE,
                VCanmouContract.TableInfo.TI_STATE);
        mTableInfoMap.put(VCanmouContract.TableInfo.TI_CODE,
                VCanmouContract.TableInfo.TI_CODE);
        mTableInfoMap.put(VCanmouContract.TableInfo.TI_NAME,
                VCanmouContract.TableInfo.TI_NAME);

        /**
         * 制作方法的投影字段
         */
        mMakeMethodMap = new HashMap<String, String>();
        mMakeMethodMap.put(VCanmouContract.MakeMethod.MM_CODE,
                VCanmouContract.MakeMethod.MM_CODE);
        mMakeMethodMap.put(VCanmouContract.MakeMethod.MM_NAME,
                VCanmouContract.MakeMethod.MM_NAME);
        mMakeMethodMap.put(VCanmouContract.MakeMethod.MM_REMARK,
                VCanmouContract.MakeMethod.MM_REMARK);

        /**
         * 制作口味的投影字段
         */
        mMakeTasteMap = new HashMap<String, String>();
        mMakeTasteMap.put(VCanmouContract.MakeTaste.MT_CODE,
                VCanmouContract.MakeTaste.MT_CODE);
        mMakeTasteMap.put(VCanmouContract.MakeTaste.MT_NAME,
                VCanmouContract.MakeTaste.MT_NAME);
        mMakeTasteMap.put(VCanmouContract.MakeTaste.MT_REMARK,
                VCanmouContract.MakeTaste.MT_REMARK);

        /**
         * 动态信息的投影字段
         */
        mDynamicInfoMap = new HashMap<String, String>();
        mDynamicInfoMap.put(VCanmouContract.DynamicInfo.DI_CODE,
                VCanmouContract.DynamicInfo.DI_CODE);
        mDynamicInfoMap.put(VCanmouContract.DynamicInfo.M_ID,
                VCanmouContract.DynamicInfo.M_ID);
        mDynamicInfoMap.put(VCanmouContract.DynamicInfo.DI_KEY,
                VCanmouContract.DynamicInfo.DI_KEY);
        mDynamicInfoMap.put(VCanmouContract.DynamicInfo.DI_NAME,
                VCanmouContract.DynamicInfo.DI_NAME);
        mDynamicInfoMap.put(VCanmouContract.DynamicInfo.DI_CONTENT,
                VCanmouContract.DynamicInfo.DI_CONTENT);
        mDynamicInfoMap.put(VCanmouContract.DynamicInfo.DI_PARAMETERS,
                VCanmouContract.DynamicInfo.DI_PARAMETERS);
    }

    @Override
    public boolean onCreate() {
        mDatabaseHelper = new VCanmouDBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
            String sortOrder) {

        // 初始化查询语句的构建器
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        /**
         * 设置要查询的表,投影区,筛选条件
         */
        switch (mUriMatcher.match(uri)) {
        default:
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    /**
     * 该方法用来进行大量数据的插入操作，通过将所有的插入操作放入一个事务(transaction)中，来提升插入操作的效率
     */
    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        int count = 0;
        long start = System.currentTimeMillis();

        // 打开数据库
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

        // 开启数据库事务
        db.beginTransaction();
        try {
            db.delete(getTable(uri), null, null);
            int num = values.length;
            for (int i = 0; i < num; i++) {
                if (db.insert(getTable(uri), null, values[i]) < 0) {
                    return count;
                }
            }
            // 设置事务操作执行成功
            db.setTransactionSuccessful();
        } finally {
            // 结束数据库事务，若事务操作没有成功，则rollback到之前状态。
            db.endTransaction();
        }
        count = values.length;

        long end = System.currentTimeMillis();
        Log.i(TAG, "bulk insert cost " + (end - start) + " ms");

        // 通知该Uri的监听者数据发生变化了
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        return db.delete(getTable(uri), where, whereArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String whereClause, String[] whereArgs) {
        int count = 0;
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        switch (mUriMatcher.match(uri)) {
        default:
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    /**
     * 对于用new Intent(String action, Uri uri)方法启动activity是很重要的，如果它返回的MIME
     * type和activity在<intent filter>中定义的data的MIME type不一致，将造成activity无法启动
     */
    @Override
    public String getType(Uri uri) {
        switch (mUriMatcher.match(uri)) {
        case CARTE:
            return VENDOR_TYPE_DIR + "/" + VENDOR_SPECIFIC
                    + VCanmouContract.Carte.TABLE_NAME;
        case CARTE_ID:
            return VENDOR_TYPE_ITEM + "/" + VENDOR_SPECIFIC
                    + VCanmouContract.Carte.TABLE_NAME;
        default:
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    /**
     * 根据uri获取匹配的表名
     * 
     * @param uri
     *            数据库表的uri
     * @return 与该uri匹配的表名
     */
    private static String getTable(Uri uri) {
        String tableName = null;
        Log.i(TAG, "mUriMatcher.match(uri) = " + mUriMatcher.match(uri));
        switch (mUriMatcher.match(uri)) {
        // 菜品表
        case CARTE:
            tableName = VCanmouContract.Carte.TABLE_NAME;
            break;
        default:
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
        return tableName;
    }

}
