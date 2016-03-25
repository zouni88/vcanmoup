package com.shenzhoumeiwei.vcanmou.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 数据库操作的一个辅助类，方便数据库的开启和升级
 */
public class VCanmouDBHelper extends SQLiteOpenHelper {

    private static final String TAG = "EMenuClerkReducedDBHelper";

    private static final String DB_NAME = "emenu_clerk_reduced.db"; // 数据库名称
    private static final int DB_VERSION = 4; // 数据库版本号(修改数据库结构时需要增加版本号，以便老版本应用升级)

    public VCanmouDBHelper(Context context) {
        this(context, DB_NAME, null, DB_VERSION);
    }

    public VCanmouDBHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * 当数据库不存在时执行onCreate()
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 菜品表
        db.execSQL("CREATE TABLE " + VCanmouContract.Carte.TABLE_NAME + "("
                + VCanmouContract.Carte._ID + " INTEGER PRIMARY KEY,"
                + VCanmouContract.Carte.C_ID + " INTEGER,"
                + VCanmouContract.Carte.CT_ID + " INTEGER,"
                + VCanmouContract.Carte.C_COMMENTS + " INTEGER,"
                + VCanmouContract.Carte.C_LIKE + " INTEGER,"
                + VCanmouContract.Carte.C_SPECIAL + " BOOLEAN,"
                + VCanmouContract.Carte.C_CODE + " TEXT,"
                + VCanmouContract.Carte.C_NAME + " TEXT,"
                + VCanmouContract.Carte.C_ENAME + " TEXT,"
                + VCanmouContract.Carte.C_UNIT + " TEXT,"
                + VCanmouContract.Carte.MM_NAMES + " TEXT,"
                + VCanmouContract.Carte.MT_NAMES + " TEXT,"
                + VCanmouContract.Carte.C_REMARK + " TEXT,"
                + VCanmouContract.Carte.PINYIN_FULL_SPELL + " TEXT,"
                + VCanmouContract.Carte.PINYIN_INITIAL_SPELL + " TEXT)");

        // 菜品规格表
        db.execSQL("CREATE TABLE " + VCanmouContract.CarteSpec.TABLE_NAME + "("
                + VCanmouContract.CarteSpec._ID + " INTEGER PRIMARY KEY,"
                + VCanmouContract.CarteSpec.CS_ID + " INTEGER,"
                + VCanmouContract.CarteSpec.C_ID + " INTEGER,"
                + VCanmouContract.CarteSpec.CP_ID + " INTEGER,"
                + VCanmouContract.CarteSpec.CS_STATE + " INTEGER,"
                + VCanmouContract.CarteSpec.CS_PRICE + " DOUBLE,"
                + VCanmouContract.CarteSpec.CS_PRICE_MEMBER + " DOUBLE,"
                + VCanmouContract.CarteSpec.CS_IS_COMBO + " BOOLEAN,"
                + VCanmouContract.CarteSpec.CS_IS_WEIGH + " BOOLEAN,"
                + VCanmouContract.CarteSpec.CS_OFF_SHELVE + " BOOLEAN,"
                + VCanmouContract.CarteSpec.CS_CODE + " TEXT,"
                + VCanmouContract.CarteSpec.CS_SPEC + " TEXT)");

        // 菜品类型表
        db.execSQL("CREATE TABLE " + VCanmouContract.CarteType.TABLE_NAME + "("
                + VCanmouContract.CarteType._ID + " INTEGER PRIMARY KEY,"
                + VCanmouContract.CarteType.CT_ID + " INTEGER,"
                + VCanmouContract.CarteType.CT_PARENT_ID + " INTEGER,"
                + VCanmouContract.CarteType.CT_CODE + " TEXT,"
                + VCanmouContract.CarteType.CT_NAME + " TEXT)");

        // 菜品性质表
        db.execSQL("CREATE TABLE " + VCanmouContract.CarteProperty.TABLE_NAME + "("
                + VCanmouContract.CarteProperty._ID + " INTEGER PRIMARY KEY,"
                + VCanmouContract.CarteProperty.CP_ID + " INTEGER,"
                + VCanmouContract.CarteProperty.CP_NAME + " TEXT)");

        // 制作方法表
        db.execSQL("CREATE TABLE " + VCanmouContract.MakeMethod.TABLE_NAME + "("
                + VCanmouContract.MakeMethod._ID + " INTEGER PRIMARY KEY,"
                + VCanmouContract.MakeMethod.MM_CODE + " TEXT,"
                + VCanmouContract.MakeMethod.MM_NAME + " TEXT,"
                + VCanmouContract.MakeMethod.MM_REMARK + " TEXT)");

        // 菜品口味表
        db.execSQL("CREATE TABLE " + VCanmouContract.MakeTaste.TABLE_NAME + "("
                + VCanmouContract.MakeTaste._ID + " INTEGER PRIMARY KEY,"
                + VCanmouContract.MakeTaste.MT_CODE + " TEXT,"
                + VCanmouContract.MakeTaste.MT_NAME + " TEXT,"
                + VCanmouContract.MakeTaste.MT_REMARK + " TEXT)");

        // 套餐菜品表
        db.execSQL("CREATE TABLE " + VCanmouContract.ComboCarte.TABLE_NAME + "("
                + VCanmouContract.ComboCarte._ID + " INTEGER PRIMARY KEY,"
                + VCanmouContract.ComboCarte.CCAR_ID + " INTEGER,"
                + VCanmouContract.ComboCarte.CS_ID + " INTEGER,"
                + VCanmouContract.ComboCarte.CG_ID + " INTEGER,"
                + VCanmouContract.ComboCarte.CCAR_MIN + " DOUBLE,"
                + VCanmouContract.ComboCarte.CCAR_MAX + " DOUBLE,"
                + VCanmouContract.ComboCarte.CCAR_PRICE + " DOUBLE)");

        // 套餐分组表
        db.execSQL("CREATE TABLE " + VCanmouContract.ComboGroup.TABLE_NAME + "("
                + VCanmouContract.ComboGroup._ID + " INTEGER PRIMARY KEY,"
                + VCanmouContract.ComboGroup.CG_ID + " INTEGER,"
                + VCanmouContract.ComboGroup.CS_ID + " INTEGER,"
                + VCanmouContract.ComboGroup.CG_COUNT + " INTEGER,"
                + VCanmouContract.ComboGroup.CG_NAME + " TEXT)");

        // 桌台信息表
        db.execSQL("CREATE TABLE " + VCanmouContract.TableInfo.TABLE_NAME + "("
                + VCanmouContract.TableInfo._ID + " INTEGER PRIMARY KEY,"
                + VCanmouContract.TableInfo.TI_ID + " INTEGER,"
                + VCanmouContract.TableInfo.TT_ID + " INTEGER,"
                + VCanmouContract.TableInfo.TI_SEAT_COUNT + " INTEGER,"
                + VCanmouContract.TableInfo.TI_STATE + " INTEGER,"
                + VCanmouContract.TableInfo.TI_CODE + " TEXT,"
                + VCanmouContract.TableInfo.TI_NAME + " TEXT)");

        // 桌台类型表
        db.execSQL("CREATE TABLE " + VCanmouContract.TableType.TABLE_NAME + "("
                + VCanmouContract.TableType._ID + " INTEGER PRIMARY KEY,"
                + VCanmouContract.TableType.TT_ID + " INTEGER,"
                + VCanmouContract.TableType.TT_CODE + " TEXT,"
                + VCanmouContract.TableType.TT_NAME + " TEXT)");

        // 动态消息表
        db.execSQL("CREATE TABLE " + VCanmouContract.DynamicInfo.TABLE_NAME + "("
                + VCanmouContract.DynamicInfo._ID + " INTEGER PRIMARY KEY,"
                + VCanmouContract.DynamicInfo.DI_CODE + " INTEGER,"
                + VCanmouContract.DynamicInfo.M_ID + " INTEGER,"
                + VCanmouContract.DynamicInfo.DI_KEY + " TEXT,"
                + VCanmouContract.DynamicInfo.DI_NAME + " TEXT,"
                + VCanmouContract.DynamicInfo.DI_CONTENT + " TEXT,"
                + VCanmouContract.DynamicInfo.DI_PARAMETERS + " TEXT)");

        // 菜品评论表
        db.execSQL("CREATE TABLE " + VCanmouContract.Comment.TABLE_NAME + "("
                + VCanmouContract.Comment._ID + " INTEGER PRIMARY KEY,"
                + VCanmouContract.Comment.CC_ID + " INTEGER,"
                + VCanmouContract.Comment.C_ID + " INTEGER,"
                + VCanmouContract.Comment.CC_CONTENT + " TEXT,"
                + VCanmouContract.Comment.CC_INPUT_DATE + " TEXT)");

        // 菜品视图
        db.execSQL("CREATE VIEW " + VCanmouContract.Dish.TABLE_NAME + " AS SELECT c."
                + VCanmouContract.Carte._ID + ",c."
                + VCanmouContract.Carte.C_ID + ",ct."
                + VCanmouContract.CarteType.CT_ID + ","
                + VCanmouContract.Dish.CS_ID + ",cp."
                + VCanmouContract.CarteProperty.CP_ID + ","
                + VCanmouContract.CarteType.CT_PARENT_ID + ","
                + VCanmouContract.Dish.C_COMMENTS + ","
                + VCanmouContract.Dish.C_LIKE + ","
                + VCanmouContract.Dish.CS_STATE + ","
                + VCanmouContract.Dish.CS_PRICE + ","
                + VCanmouContract.Dish.CS_PRICE_MEMBER + ","
                + VCanmouContract.Dish.C_SPECIAL + ","
                + VCanmouContract.Dish.CS_IS_COMBO + ","
                + VCanmouContract.Dish.CS_IS_WEIGH + ","
                + VCanmouContract.Dish.CS_OFF_SHELVE + ","
                + VCanmouContract.Dish.C_CODE + ","
                + VCanmouContract.Dish.C_NAME + "||'('||"
                + VCanmouContract.CarteSpec.CS_SPEC + "||')' AS "
                + VCanmouContract.Dish.C_NAME + ","
                + VCanmouContract.Dish.C_ENAME + ","
                + VCanmouContract.Dish.C_UNIT + ","
                + VCanmouContract.Dish.MM_NAMES + ","
                + VCanmouContract.Dish.MT_NAMES + ","
                + VCanmouContract.Dish.C_REMARK + ","
                + VCanmouContract.Dish.CP_NAME + ","
                + VCanmouContract.Dish.CS_CODE + ","
                + VCanmouContract.Dish.CS_SPEC + ","
                + VCanmouContract.Dish.PINYIN_FULL_SPELL + ","
                + VCanmouContract.Dish.PINYIN_INITIAL_SPELL + " FROM "
                + VCanmouContract.CarteType.TABLE_NAME + " ct LEFT JOIN "
                + VCanmouContract.Carte.TABLE_NAME + " c ON ct."
                + VCanmouContract.CarteType.CT_ID + " = c."
                + VCanmouContract.Carte.CT_ID + " LEFT JOIN "
                + VCanmouContract.CarteSpec.TABLE_NAME + " cs ON c."
                + VCanmouContract.Carte.C_ID + " = cs."
                + VCanmouContract.CarteSpec.C_ID + " LEFT JOIN "
                + VCanmouContract.CarteProperty.TABLE_NAME + " cp ON cs."
                + VCanmouContract.CarteSpec.CP_ID + "= cp."
                + VCanmouContract.CarteProperty.CP_ID + " ORDER BY ct."
                + VCanmouContract.CarteType.CT_ID);
    }

    /**
     * 当版本号升级时执行onUpgrade()
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + VCanmouContract.Carte.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VCanmouContract.CarteSpec.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VCanmouContract.CarteType.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VCanmouContract.CarteProperty.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VCanmouContract.MakeMethod.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VCanmouContract.MakeTaste.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VCanmouContract.ComboCarte.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VCanmouContract.ComboGroup.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VCanmouContract.TableInfo.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VCanmouContract.TableType.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VCanmouContract.DynamicInfo.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VCanmouContract.Comment.TABLE_NAME);
        db.execSQL("DROP VIEW IF EXISTS " + VCanmouContract.Dish.TABLE_NAME);
        onCreate(db);
    }

}
