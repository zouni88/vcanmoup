package com.shenzhoumeiwei.vcanmou.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * EMenuProvider的Contract，用来向外界暴露表的uri及列名
 */
public abstract class VCanmouContract {

    /**
     * ContentProvider的Authority
     */
    public static final String AUTHORITY = "com.hotspot.emenuclerkreduced.provider";

    private static final String BASE_CONTENT_URI = "content://" + AUTHORITY + "/";

    /**
     * 菜品表
     */
    public interface Carte extends BaseColumns {
        // 表名
        public static final String TABLE_NAME = "carte";

        // 列名
        public static final String C_ID = "c_id"; // 菜品id(INTEGER)
        public static final String CT_ID = "ct_id"; // 菜品类型id(INTEGER)
        public static final String C_COMMENTS = "c_comments"; // 评论数量(INTEGER)
        public static final String C_LIKE = "c_like"; // 赞数量(INTEGER)
        public static final String C_SPECIAL = "c_special"; // 特色新品(INTEGER)
        public static final String C_CODE = "c_code"; // 菜品代码(TEXT)
        public static final String C_NAME = "c_name"; // 菜品名称(TEXT)
        public static final String C_ENAME = "c_ename"; // 菜品英文名称(TEXT)
        public static final String C_UNIT = "c_unit"; // 菜品单位(TEXT)
        public static final String MM_NAMES = "mm_names"; // 制作方法名称(TEXT)
        public static final String MT_NAMES = "mt_names"; // 菜品口味名称(TEXT)
        public static final String C_REMARK = "c_remark"; // 菜品描述(TEXT)
        public static final String PINYIN_FULL_SPELL = "pinyin_full_spell"; // 菜品中文全拼(TEXT)
        public static final String PINYIN_INITIAL_SPELL = "pinyin_initial_spell"; // 菜品中文拼音首字母(TEXT)

        public static final Uri CONTENT_URI = Uri.parse(BASE_CONTENT_URI + TABLE_NAME);
    }

    /**
     * 菜品规格表
     */
    public interface CarteSpec extends BaseColumns {
        // 表名
        public static final String TABLE_NAME = "carte_spec";

        // 列名
        public static final String CS_ID = "cs_id"; // 菜品规格id(INTEGER)
        public static final String C_ID = "c_id"; // 菜品id(INTEGER)
        public static final String CP_ID = "cp_id"; // 菜品性质id(INTEGER)
        public static final String CS_STATE = "cs_state"; // 菜品状态[1:沽清,0:正常](INTEGER)
        public static final String CS_PRICE = "cs_price"; // 菜品售价(DOUBLE)
        public static final String CS_PRICE_MEMBER = "cs_price_member"; // 菜品会员价(DOUBLE)
        public static final String CS_IS_COMBO = "cs_is_combo"; // 是否套餐(BOOLEAN)
        public static final String CS_IS_WEIGH = "cs_is_weigh"; // 是否是付称菜(BOOLEAN)
        public static final String CS_OFF_SHELVE = "cs_off_shelve"; // 是否下架(BOOLEAN)
        public static final String CS_CODE = "cs_code"; // 菜品规格代码(TEXT)
        public static final String CS_SPEC = "cs_spec"; // 菜品规格(TEXT)

        public static final Uri CONTENT_URI = Uri.parse(BASE_CONTENT_URI + TABLE_NAME);
    }

    /**
     * 菜品类型表
     */
    public interface CarteType extends BaseColumns {
        // 表名
        public static final String TABLE_NAME = "carte_type";

        // 列名
        public static final String CT_ID = "ct_id"; // 菜品类型id(INTEGER)
        public static final String CT_PARENT_ID = "ct_parent_id"; // 菜品上级类型id(INTEGER)
        public static final String CT_CODE = "ct_code"; // 菜品类型代码(TEXT)
        public static final String CT_NAME = "ct_name"; // 菜品类型名称(TEXT)

        public static final Uri CONTENT_URI = Uri.parse(BASE_CONTENT_URI + TABLE_NAME);
    }

    /**
     * 菜品性质表
     */
    public interface CarteProperty extends BaseColumns {
        // 表名
        public static final String TABLE_NAME = "carte_property";

        // 列名
        public static final String CP_ID = "cp_id"; // 菜品性质id(INTEGER)
        public static final String CP_NAME = "cp_name"; // 菜品性质名称(TEXT)

        public static final Uri CONTENT_URI = Uri.parse(BASE_CONTENT_URI + TABLE_NAME);
    }

    /**
     * 制作方法表
     */
    public interface MakeMethod extends BaseColumns {
        // 表名
        public static final String TABLE_NAME = "make_method";

        // 列名
        public static final String MM_CODE = "mm_code"; // 制作方法代码(TEXT)
        public static final String MM_NAME = "mm_name"; // 制作方法名称(TEXT)
        public static final String MM_REMARK = "mm_remark"; // 制作方法备注(TEXT)

        public static final Uri CONTENT_URI = Uri.parse(BASE_CONTENT_URI + TABLE_NAME);
    }

    /**
     * 菜品口味表
     */
    public interface MakeTaste extends BaseColumns {
        // 表名
        public static final String TABLE_NAME = "make_taste";

        // 列名
        public static final String MT_CODE = "mt_code"; // 菜品口味代码(TEXT)
        public static final String MT_NAME = "mt_name"; // 菜品口味名称(TEXT)
        public static final String MT_REMARK = "mt_remark"; // 菜品口味备注(TEXT)

        public static final Uri CONTENT_URI = Uri.parse(BASE_CONTENT_URI + TABLE_NAME);
    }

    /**
     * 套餐菜品表
     */
    public interface ComboCarte extends BaseColumns {
        // 表名
        public static final String TABLE_NAME = "combo_carte";

        // 列名
        public static final String CCAR_ID = "ccar_id"; // 套餐菜ID(INTEGER)
        public static final String CS_ID = "cs_id"; // 规格ID(INTEGER)
        public static final String CG_ID = "cg_id"; // 套餐组ID(INTEGER)
        public static final String CCAR_MIN = "ccar_min"; // 最小数量(DOUBLE)
        public static final String CCAR_MAX = "ccar_max"; // 最大数量(DOUBLE)
        public static final String CCAR_PRICE = "ccar_price"; // 套餐菜品价钱(DOUBLE)

        public static final Uri CONTENT_URI = Uri.parse(BASE_CONTENT_URI + TABLE_NAME);
    }

    /**
     * 套餐分组表
     */
    public interface ComboGroup extends BaseColumns {
        // 表名
        public static final String TABLE_NAME = "combo_group";

        // 列名
        public static final String CG_ID = "cg_id"; // 套餐组ID(INTEGER)
        public static final String CS_ID = "cs_id"; // 规格ID(INTEGER)
        public static final String CG_COUNT = "cg_count"; // 套餐组数量(INTEGER)
        public static final String CG_NAME = "cg_name"; // 套餐组名称(TEXT)

        public static final Uri CONTENT_URI = Uri.parse(BASE_CONTENT_URI + TABLE_NAME);
    }

    /**
     * 桌台信息表
     */
    public interface TableInfo extends BaseColumns {
        // 表名
        public static final String TABLE_NAME = "table_info";

        // 列名
        public static final String TI_ID = "ti_id"; // 桌台id(INTEGER)
        public static final String TT_ID = "tt_id"; // 桌台类型id(INTEGER)
        public static final String TI_SEAT_COUNT = "ti_seat_count"; // 桌台餐位数(INTEGER)
        public static final String TI_STATE = "ti_state"; // 桌台状态(INTEGER)
        public static final String TI_CODE = "ti_code"; // 桌台代码(TEXT)
        public static final String TI_NAME = "ti_name"; // 桌台名称(TEXT)

        public static final Uri CONTENT_URI = Uri.parse(BASE_CONTENT_URI + TABLE_NAME);
    }

    /**
     * 桌台类型表
     */
    public interface TableType extends BaseColumns {
        // 表名
        public static final String TABLE_NAME = "table_type";

        // 列名
        public static final String TT_ID = "tt_id"; // 桌台类型id(INTEGER)
        public static final String TT_CODE = "tt_code"; // 桌台类型代码(TEXT)
        public static final String TT_NAME = "tt_name"; // 桌台类型名称(TEXT)

        public static final Uri CONTENT_URI = Uri.parse(BASE_CONTENT_URI + TABLE_NAME);
    }

    /**
     * 动态消息
     */
    public interface DynamicInfo extends BaseColumns {
        // 表名
        public static final String TABLE_NAME = "dynamic_info";

        // 列名
        public static final String DI_CODE = "di_code"; // 消息代码(INTEGER)
        public static final String M_ID = "m_id"; // 模块id(INTEGER)
        public static final String DI_KEY = "di_key"; // 消息key(TEXT)
        public static final String DI_NAME = "di_name"; // 消息名称(TEXT)
        public static final String DI_CONTENT = "di_content"; // 消息内容(TEXT)
        public static final String DI_PARAMETERS = "di_parameters"; // 消息参数(TEXT)

        public static final Uri CONTENT_URI = Uri.parse(BASE_CONTENT_URI + TABLE_NAME);
    }

    /**
     * 菜品评论表
     */
    public interface Comment extends BaseColumns {
        // 表名
        public static final String TABLE_NAME = "comment";

        // 列名
        public static final String CC_ID = "cc_id"; // 菜品评论id(INTEGER)
        public static final String C_ID = "c_id"; // 菜品id(INTEGER)
        public static final String CC_CONTENT = "cc_content"; // 菜品评论内容(TEXT)
        public static final String CC_INPUT_DATE = "cc_input_date"; // 录入时间(TEXT)

        public static final Uri CONTENT_URI = Uri.parse(BASE_CONTENT_URI + TABLE_NAME);
    }

    /**
     * 菜品视图(包含了菜品表[Carte],菜品规格表[CarteSpec],菜品性质表[CarteProperty]的所有列)
     */
    public interface Dish {
        // 表名
        public static final String TABLE_NAME = "view_dish";

        // 列名
        public static final String _ID = "_id"; // _id(INTEGER)
        public static final String C_ID = "c_id"; // 菜品id(INTEGER)
        public static final String CT_ID = "ct_id"; // 菜品类型id(INTEGER)
        public static final String CT_PARENT_ID = "ct_parent_id"; // 菜品上级类型id(INTEGER)
        public static final String CS_ID = "cs_id"; // 菜品规格id(INTEGER)
        public static final String CP_ID = "cp_id"; // 菜品性质id(INTEGER)
        public static final String C_COMMENTS = "c_comments"; // 评论数量(INTEGER)
        public static final String C_LIKE = "c_like"; // 赞数量(INTEGER)
        public static final String CS_STATE = "cs_state"; // 菜品状态[1:沽清,0:正常](INTEGER)
        public static final String CS_PRICE = "cs_price"; // 菜品售价(DOUBLE)
        public static final String CS_PRICE_MEMBER = "cs_price_member"; // 菜品会员价(DOUBLE)
        public static final String C_SPECIAL = "c_special"; // 特色新品(INTEGER)
        public static final String CS_IS_COMBO = "cs_is_combo"; // 是否套餐(BOOLEAN)
        public static final String CS_IS_WEIGH = "cs_is_weigh"; // 是否是付称菜(BOOLEAN)
        public static final String CS_OFF_SHELVE = "cs_off_shelve"; // 是否下架(BOOLEAN)
        public static final String C_CODE = "c_code"; // 菜品代码(TEXT)
        public static final String C_NAME = "c_name"; // 菜品名称(TEXT)
        public static final String C_ENAME = "c_ename"; // 菜品英文名称(TEXT)
        public static final String C_UNIT = "c_unit"; // 菜品单位(TEXT)
        public static final String MM_NAMES = "mm_names"; // 制作方法名称(TEXT)
        public static final String MT_NAMES = "mt_names"; // 菜品口味名称(TEXT)
        public static final String C_REMARK = "c_remark"; // 菜品描述(TEXT)
        public static final String CP_NAME = "cp_name"; // 菜品性质名称(TEXT)
        public static final String CS_CODE = "cs_code"; // 菜品规格代码(TEXT)
        public static final String CS_SPEC = "cs_spec"; // 菜品规格(TEXT)
        public static final String PINYIN_FULL_SPELL = "pinyin_full_spell"; // 菜品中文全拼(TEXT)
        public static final String PINYIN_INITIAL_SPELL = "pinyin_initial_spell"; // 菜品中文拼音首字母(TEXT)

        public static final Uri CONTENT_URI = Uri.parse(BASE_CONTENT_URI + TABLE_NAME);

        /**
         * 搜索菜品的uri
         */
        public static final Uri CONTENT_URI_SEARCH = Uri.parse(BASE_CONTENT_URI + TABLE_NAME
                + "/search/");
    }

}
