package com.fawvw.ms.voucher.basedomain.constants;
/*
 * Project: com.fawvw.ms.oneappserver.core.constants
 *
 * File Created at 2019-08-11
 *
 * Copyright 2019 FAW-VW Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * FAW-VW Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author zhangyunjiao
 * @Type VoucherConstants
 * @Desc
 * @date 2019-08-11 16:04
 */
//TODO: zhangyunjiao xx_App_key 是否以环境有关，是的话，需要整合到配置中
public final class VoucherConstants {

    private VoucherConstants() {
    }

    private static final String ZERO = "0";
    private static final String ONE = "1";
    public static final String TWO = "2";
    private static final String THREE = "3";
    public static final String FOUR = "4";
    private static final String FIVE = "5";
    private static final String SIX = "6";
    public static final int INT_ONE = 1;
    public static final int INT_TWO = 2;
    public static final int INT_THREE = 3;
    public static final int INT_FOUR = 4;
    public static final int INT_SIX = 6;
    private static final String MAINTAIN = "保养";
    private static final String HAVE_EXPIRED = "已过期";
    private static final String TAKING_OUT_AND_PLACING_IN_WAGONS = "取送车";
    private static final String SUCCESS_VALUE = "成功";
    private static final String FAIL_VALUE = "失败";
    public static final Byte REDEEM_VALUE_VOUCHER = 1; //代金劵
    public static final Byte PACKAGE_VOUCHER = 2; //套餐劵
    public static final Byte GOODS_VOUCHER = 3; //实物劵
    public static final Byte RIGHT_VOUCHER = 4; //权益劵
    public static final Byte THIRDPART_VOUCHER = 5; //异业劵

    //限定业务类型: 1 不限制，2 保养，3 取送车，4 维修保养
    public static final Byte NO_LIMIT_BUSSINESS = 1;
    public static final Byte MAINTAION_BUSSINESS = 2;
    public static final Byte TAKE_AND_DELIVER_VECHILE_BUSSINESS = 3;
    public static final Byte REPAIR_AND_MAINTAION_BUSSINESS = 4;

    public static final String CDP_TAKE_AND_DELIVER_VECHILE_BUSSINESS = FOUR;

    public static final Map<String, String> BUSSINESS_VALUE_OPTION = new HashMap<String, String>() {{
            put(ZERO, "首保");
            put(ONE, "索赔");
            put(TWO, MAINTAIN);
            put(THREE, "小修");
            put(FOUR, "大修");
            put(FIVE, "事故");
            put(SIX, "返工");
            put("7", "年审");
            put("8", "PDI");
            put("9", "内修");
            put("A", "保险");
            put("E", "延保");
            put("G", "优惠索赔");
            put("M", "免费检测");
            put("P", "预PDI");
            put("S", "市场服务");
            put("U", "外出救援");
            put("Z", "召回");
        }
    };

    public static final Byte SAVE = 1; //已保存
    public static final Byte GENERATE = 2; //已生成
    public static final Byte EXPIRED = 3; //已过期
    public static final Byte FINISH = 4; //已终止
    public static final Byte NO_STOCK = 5; //已领完
    public static final String GENERATE_VOUCHER_DJQ_TEMPLATE_URL = "weCardAPI/inpublic/v1/ticketGeneratetDjq";
    public static final String GENERATE_VOUCHER_SWQ_TEMPLATE_URL = "weCardAPI/inpublic/v1/ticketGeneratetSwq";
    public static final String GENERATE_VOUCHER_TCQ_TEMPLATE_URL = "weCardAPI/inpublic/v1/ticketGeneratetTcq";
    public static final String GENERATE_VOUCHER_YYQ_TEMPLATE_URL = "weCardAPI/inpublic/v1/ticketGeneratetYyq";
    public static final String GENERATE_REDEEM_TEMPLATE_URL = "accounting/inpublic/v1/templateDmsUpdate";
    public static final String GENERATE_COMMON_REDEEM_TEMPLATE_URL = "accounting/inpublic/v1/templateUpdate";
    public static final String GET_USER_VOUCHER_LIST_URL = "weCardAPI/public/v1/queryTicketListClient";
    public static final String ABNDON_VOUCHER_TEMPLATE_URL = "weCardAPI/inpublic/v1/ticketAbndon";
    public static final String ADD_VOUCHER_BATCH_STORE_COUNT_URL = "weCardAPI/inpublic/v1/ticketNumExt";
    public static final String IMPORT_REDEEM_CODE_URL = "weCardAPI/inpublic/v1/importRedeemcode";
    public static final Byte FIXED_EXPIRED_DATE = 0;
    public static final Byte DYNAMIC_EXPIRED_DATE = 1;//UAT APP key
    public static final String SERVICE_APP_KEY = "0665529604";
    public static final String MINI_APP_KEY = "9128148674";
    public static final String WEB_APP_KEY = "4722332442";
    public static final String ANDROID_APP_KEY = "7463135097";
    public static final String IOS_APP_KEY = "5214621308";
    public static final String VECHILE_MINI_APP_KEY = "2985644640";
    public static final String ACTIVITY_MINI_APP_KEY = "3383740429";
    public static final String COMMITITY_APP_KEY = "5402294258";
    public static final List<String> APP_KEYS = Arrays.asList(SERVICE_APP_KEY,
        MINI_APP_KEY, WEB_APP_KEY,
        ANDROID_APP_KEY, IOS_APP_KEY, VECHILE_MINI_APP_KEY, ACTIVITY_MINI_APP_KEY,
        COMMITITY_APP_KEY);
    public static final Byte DELETE = 1; //已保存
    public static final Byte AUTOMATIC = 1;//自动发放
    public static final Byte MYWELFARE = 2;//推送至我的福利
    public static final Byte TAKE_THE_CAR = 3;//推送至取送车
    public static final Byte DIRECTING_GRANT = 5;//指定发放
    public static final Byte ENABLE = 1;//启用
    public static final Byte DISABLE = 0;//禁用
    public static final Byte NOT_DELETE = 0;//未删除
    public static final String OEM_CHANNEL = "OEM";
    public static final String DLR_CHANNEL = "DLR";
    public static final String DEALER_CHANNEL = "DEALER";
    public static final String CARDTICKETKEY = "609336761578946560";
    public static final Integer TIMES_ZERO = 0;//剩余领取次数为0
    public static final Byte FIXED = 0;//固定有效期
    public static final Byte DYNAMIC = 1;//动态有效期
    public static final Byte MAINTENANCE = 2;//保养
    public static final Byte SERVICE_MAINTENANCE = 4;//维修保养

    public static final String GRANT = "weCardAPI/inpublic/v1/ticketGrantDMS";//卡券发放
    public static final String VOUCHERINFO = "weCardAPI/inpublic/v1/queryTicketDetail";//卡券详情查询
    public static final String IS_REGISTERED = "user/internal/v1/account/checkAccountIsRegistered";//检查账号是否已注册
    public static final String USERINFO = "user/internal/v1/getUserAccount";//获取用户账户信息
    public static final String CARINFO = "vehicle/internal/v1/getVehicleDetail";//获取车辆详情
    public static final String GET_AID = "vehicle/internal/v1/getOwnerAidByVin";//根据vin获取车主aid
    public static final String GET_USER = "user/inpublic/v1/getSimpleUserInfoByAids";//根据aid获取用户信息
    public static final String VOUCHER_NUM = "weCardAPI/inpublic/v1/queryTicketStatusNumServer";//卡券数量查询
    //public static final String TICKET_LIST = "weCardAPI/inpublic/v1/queryTicketListServer";//卡券列表
    public static final String DURATION_VOUCHER = "accounting/inpublic/v1/durationTicket";//卡劵冻结
    public static final String CANCEL_DURATION_VOUCHER = "accounting/inpublic/v1/canceldurationTicket";//卡券取消冻结
    public static final String COMMON_REDEEM_VOUCHER = "accounting/inpublic/v1/cardTicket";//卡券核销（通用）
    public static final String CHANGE_CODE_ABNDON = "weCardAPI/inpublic/v1/changecodeAbndon";//卡券作废
    public static final String DMS_REDEEM_VOUCHER = "accounting/inpublic/v1/cardTicketDms";//卡券核销（DMS）

    public static final String BRAND_VM = "VW";
    public static final String BRAND = "brand";
    public static final Map<Byte, String> BUSSINESS_CODE_TO_CDP_CODE = new HashMap<Byte, String>() {{
            put(NO_LIMIT_BUSSINESS, THREE);
            put(MAINTAION_BUSSINESS, TWO);
            put(TAKE_AND_DELIVER_VECHILE_BUSSINESS, FOUR);
            put(REPAIR_AND_MAINTAION_BUSSINESS, ONE);
        }
    };

    public static final Byte NOT_RECIVE_VOUCHER = 1; //未领取
    public static final Byte ALREADY_WITH_DRAW_VOUCHER = 2; //已撤回
    public static final Byte ALREADY_RECIVE_VOUCHER = 3; //已领取
    public static final Byte ALREADY_FREEZE_VOUCHER = 4; //已冻结
    public static final Byte ALREADY_USED_VOUCHER = 5; //已使用
    public static final Byte ALREADY_EXPIRED_VOUCHER = 6; //已过期
    public static final Byte ALREADY_GRANT = 0;//已发放
    public static final Byte ALREADY_WITH_OUT_GRANT = 1;//已撤回
    public static final Map<Byte, String> USER_VOUCHER_STATUS = new HashMap<Byte, String>() {{
            put(NOT_RECIVE_VOUCHER, "未领取");
            put(ALREADY_WITH_DRAW_VOUCHER, "已撤回");
            put(ALREADY_RECIVE_VOUCHER, ALREADY_RECEIVED);
            put(ALREADY_FREEZE_VOUCHER, "已冻结");
            put(ALREADY_USED_VOUCHER, ALREADY_USED);
            put(ALREADY_EXPIRED_VOUCHER, HAVE_EXPIRED);
        }
    };

    public static final Map<Byte, String> VOUCHER_TYPE_MAP = new HashMap<Byte, String>() {{
            put(REDEEM_VALUE_VOUCHER, "代金劵");
            put(PACKAGE_VOUCHER, "套餐劵");
            put(GOODS_VOUCHER, "实物劵");
            put(RIGHT_VOUCHER, "权益劵");
        }
    };

    public static final Map<Byte, String> VOUCHER_TYPE_TO_CDP_TYPE_MAP = new HashMap<Byte, String>() {{
            put(REDEEM_VALUE_VOUCHER, TWO);
            put(PACKAGE_VOUCHER, TWO);
            put(GOODS_VOUCHER, FOUR);
            put(RIGHT_VOUCHER, FOUR);
        }
    };

    public static final Map<Byte, String> BUSINESS_TYPE_MAP = new HashMap<Byte, String>() {{
            put(NO_LIMIT_BUSSINESS, "不限制");
            put(MAINTAION_BUSSINESS, MAINTAIN);
            put(TAKE_AND_DELIVER_VECHILE_BUSSINESS, TAKING_OUT_AND_PLACING_IN_WAGONS);
            put(REPAIR_AND_MAINTAION_BUSSINESS, "维修保养");
        }
    };

    public static final Map<String, Byte> CPD_TYPE_TO_BUSINESS_TYPE_MAP = new HashMap<String, Byte>() {{
            put(THREE, NO_LIMIT_BUSSINESS);
            put(TWO, MAINTAION_BUSSINESS);
            put(FOUR, TAKE_AND_DELIVER_VECHILE_BUSSINESS);
            put(ONE, REPAIR_AND_MAINTAION_BUSSINESS);
        }
    };

    public static final Map<Byte, String> BATCH_STATUS_MAP = new HashMap<Byte, String>() {{
            put(SAVE, "已保存");
            put(GENERATE, "已生成");
            put(EXPIRED, HAVE_EXPIRED);
            put(FINISH, "已终止");
        }
    };

    public static final Integer MAX_EXCEL_EXPORT_NUM = 10000;

    public static final String CDP_CARD_KEY = "cardTicketKey";
    public static final String CDP_GENERATE_ID = "generateId";
    public static final String CDP_CARD_TYPE = "cardTicketType";
    public static final String CDP_PACKAGE_CARD = FIVE;
    public static final String CARD_TICKET_STATUS = "30";//cdp卡券已使用
    public static final String CARD_TICKET_EXPIRED_STATUS = "40";//cdp卡券已无效
    public static final Integer GRANT_VOUCHER_MAX_NUM = 8000;
    public static final String CHANGE_CODE_ID = "changecodeId";//cdp 卡券ID
    public static final String DETAIL_URL = "faw-vw://coupon_list/grant";//发送消息调整路径
    public static final String TITLE = "优惠券发放提醒";//发送消息标题
    public static final Byte REDEEM_NOT_IN_DMS = 1; //不在DMS核销
    public static final Byte REDEEM_IN_DMS = 2; //在DMS核销
    public static final Byte DEFAULT_REDEEM_TYPE = 0; //默认核销类型,非dms
    public static final String IMAGE_URL = "https://one-app-dev-2-1256532032.piccd.myqcloud.com/31670ebf54e14694ac61b081322482b0.png";
    public static final String VOUCHER = "VOUCHER";
    public static final Byte BINDING_CAR = 1;//已绑车
    public static final Byte NOT_BINDING_CAR = 0;//未绑车
    public static final Byte MESSAGE_NOTICE = 1;//应用消息提醒
    public static final Byte POP_UP_MESSAGE_NOTICE = 1;//弹屏消息提醒
    public static final Byte ALL_DEALERS = 0;//全部经销商
    public static final Byte SOME_DEALERS = 1;//指定经销商
    public static final Byte NO_LIMITED_VIN = 0;//不限制vin
    public static final Byte LIMITED_VIN = 1;//限制vin
    public static final String CDP_MJQ_CARD = ONE; //满减劵
    public static final String CDP_DJQ_CARD = TWO; //代金劵
    public static final String CDP_ZKQ_CARD = THREE; //折扣劵
    public static final String CDP_SWQ_CARD = FOUR; //实物劵
    public static final String CDP_TCQ_CARD = FIVE; //套餐劵
    public static final String CDP_YYQ_CARD = SIX; //异业劵
    public static final Map<String, Byte> CART_TICKET_TYPE_TO_VOUCHER_TYPE = new HashMap<String, Byte>() {{
            put(CDP_MJQ_CARD, REDEEM_VALUE_VOUCHER);
            put(CDP_DJQ_CARD, REDEEM_VALUE_VOUCHER);
            put(CDP_ZKQ_CARD, REDEEM_VALUE_VOUCHER);
            put(CDP_SWQ_CARD, GOODS_VOUCHER);
            put(CDP_TCQ_CARD, PACKAGE_VOUCHER);
            put(CDP_YYQ_CARD, RIGHT_VOUCHER);
        }
    };
    //自动发放锁
    public static final String AUTOMATIC_GRANT_VOUCHER_LOCK = "AUTOMATIC_GRANT_VOUCHER_LOCK_%s";
    //筛选发放锁
    public static final String FILTER_GRANT_VOUCHER_LOCK = "FILTER_GRANT_VOUCHER_LOCK_%s";
    //我的福利发放锁
    public static final String MY_WELFARE_GRANT_VOUCHER_LOCK = "MY_WELFARE_GRANT_VOUCHER_LOCK_%s";
    //经销商发放锁
    public static final String DEALER_GRANT_VOUCHER_LOCK = "DEALER_GRANT_VOUCHER_LOCK_%s";
    //指定发放锁
    public static final String DIRECTING_GRANT_VOUCHER_LOCK = "DIRECTING_GRANT_VOUCHER_LOCK_%s";

    public static final String VOUCHER_MSG_CONTENT = "恭喜您有%d张（%s)，已发放到您的券包中，赶紧去看看吧！";//优惠劵劵发送消息内容

    public static final Map<Byte, String> GRANT_BUSINESS_TYPE_MAP = new HashMap<Byte, String>() {{
            put(Byte.valueOf(ZERO), "");
            put(Byte.valueOf(ONE), TAKING_OUT_AND_PLACING_IN_WAGONS);
            put(Byte.valueOf(TWO), "维保预约");
            put(Byte.valueOf(THREE), "延时服务");
        }
    };

    public static final Map<Byte, String> GRANT_TYPE_MAP = new HashMap<Byte, String>() {{
            put(Byte.valueOf(ONE), "自动发放");
            put(Byte.valueOf(TWO), "推送至我的福利");
            put(Byte.valueOf(THREE), "推送至取送车福利");
            put(Byte.valueOf(FOUR), "筛选发放");
            put(Byte.valueOf(FIVE), "指定发放");
        }
    };

    public static final Byte SELECT_GRANT = 4;

    public static final Byte GRANT_BUSINESS_NA = 0;

    public static final String CROSSING_SPLIT = "-";

    public static final Byte MOBILE_TYPE = 1;

    //public static final Byte GRANTEE_NOT_CHECK_ = 0;

    public static final Byte GRANTEE_CHECK_SUCESS = 1;

    public static final Byte GRANTEE_CHECK_FAIL = 2;

    public static final Byte GRANTEE_MOBILE_TYPE = 1;

    public static final Byte GRANTEE_VIN_TYPE = 2;

    public static final String GRANTEE_CHECK_MOBILE_FAIL_REASON = "手机号未注册";

    public static final String GRANTEE_CHECK_VIN_FAIL_REASON = "VIN码未绑定";

    public static final Integer MAX_CHECK_NUM = 500;

    public static final String MOBILE_NOT_REGISTER = GRANTEE_CHECK_MOBILE_FAIL_REASON;

    public static final String MOBILE_FORMAT_ERROR = "手机号格式错误";

    public static final Pattern MOBILE_PATTERN = Pattern
        .compile("^[1][1,2,3,4,5,6,7,8,9][0-9]{9}$");

    public static final String VIN_NOT_BIND = "vin码未绑定";

    public static final Byte MANUAL_IMPORT = 1; //手动导入

    public static final Byte EXCEL_IMPORT = 2; //批量导入

    public static final String NICK_NAME_PRE = "用户_%s";

    public static final Integer MAX_VOUCHER_DETAIL_COUNT = 2000;

    public static final Integer MAX_GRANT_VOUCHER_COUNT = 5000;

    public static final Integer MAX_SHEET_VOUCHER_COUNT = 800000;

    public static final Integer ONE_KB = 1024;

    public static final Integer YESTERDAY = -1;

    public static final Integer INIT_OFFSET = 0;

    public static final Integer FIRST_SHEET = 0;

    public static final Byte NOT_CHECK = 0;

    public static final Byte SUCCESS = 1;

    public static final Byte FAIL = 2;

    public static final Map<Byte, String> GRANTEE_CHECK_STATUS = new HashMap<Byte, String>() {{
            put(NOT_CHECK, "未检测");
            put(SUCCESS, SUCCESS_VALUE);
            put(FAIL, FAIL_VALUE);
        }
    };

    public static final Map<Byte, String> SEND_SMS_STATUS = new HashMap<Byte, String>() {{
            put(NOT_CHECK, "未发送");
            put(SUCCESS, SUCCESS_VALUE);
            put(FAIL, FAIL_VALUE);
        }
    };

    public static final Integer IMPORT_GRPGRESS_FINISH = 100;

    public static final long ONE_DAY = 86400;

    public static final String REDEEM_VOUCHER_SMS_CONTENT = "%s元立减%s元";//代金优惠劵立减短信内容

    public static final String GOODS_VOUCHER_SMS_CONTENT = "免费领取价值%s元的礼品";//实物优惠劵立减短信内容

    public static final String MOBILE_SMS_TEMPLATE = "TPL_0270";

    public static final String VIN_SMS_TEMPLATE = "TPL_0271";
    //不显示
    public static final Byte CODE_TYPE_EMPTY = 0;
    //显示
    public static final Byte CODE_TYPE_SIGNAL = 1;

    public static final Byte CODE_TYPE_THIRD = 2;

    public static final Byte THIRDPART_CODE = 1;

    public static final Byte NOT_THIRDPART_CODE = 0;

    public static final Integer MAX_BATCH_COUNT = 5000;

    public static final Integer MAX_IMPORT_CODE_COUNT = 1000;

    public static final Byte THIRDPART_SHOP = 1;

    public static final Integer CURRENT_PAGE = 1;

    public static final Integer PAGE_SIZE = 5;

    public static final String AVAILABLE_STATUS = "20";

    public static final String NA_BUSINESS = "-1";

    public static final Integer DEFAULT_FAKE_VALUE = 0;

    public static final Integer DEFAULT_PAGE_SIZE = 10;

    public static final Integer INVALID_USER_ID = -1;

    public static final long WAIT_LOCK_TIME = 4;//锁等待时间

    public static final long RELEASE_LOCK_TIME = 5;//锁释放时间

    public static final Integer DEFAULT_LAST_TIMES = 1;

    public static final Integer DEFAULT_PAGE = 1;
    //维保预约
    public static final Byte GRANT_MAINTAION_AND_ORDER_TYPE = 2;

    public static final Byte WEEKDAY = 2;

    public static final Byte WORK_DAY = 1;

    public static final int NOT_EXIST_USER_ID = -1;

    public static final String VOUCHER_GRANT_LIST_ORDER_ONE = "b.create_time desc";
    public static final String VOUCHER_GRANT_LIST_ORDER_TWO = "b.status,b.id desc";
    //TODO 暂时给卡券一个默认状态为未领取   定义“100”是为了不和cdp返回的卡券状态码产生冲突
    public static final String UNCLAIMED_STATUS = "100";
    public static final String VOUCHER_GRANT_LIST_NAME = "查询的卡券列表为:";
    public static final String SEND_MSG_RESULT = "发送消息返回的数据为:";
    public static final String RETURN_STATUS = "returnStatus";
    public static final String RESULT_STATUS = "SUCCESS";
    public static final String RESULT_DATA = "data";
    public static final String REDEEM_CODE = "redeemcode";
    public static final String DEALER_CODE_LIST = "dealercodeList";
    public static final String CARD_TICKET_STATUS_KEY = "cardTicketStatus";
    public static final String BATCH_ID = "batchId";
    public static final String VIN = "vin";
    public static final String BUSINESS_CODE = "businessCode";
    public static final String FAILED = "FAILED";
    public static final String CITY_SUFFIX = "00";
    public static final String STRING_NULL = "null";
    public static final String RETURN_MESSAGE = "returnMessage";
    public static final String SEPARATOR = ",";
    public static final List<String> MUNICIPALITIES = Arrays
        .asList("110100", "310100", "120100", "500100");
    public static final String PROVINCE_SUFFIX = "0000";
    public static final String REF_ID_TO_OPERATION_USER_MAP_CACHE_KEY = "refId2operationUser";
    public static final String AUTHORIZATION = "Authorization";
    public static final String SALE_DATE = "saleDate";
    public static final String LAST_BACK_STORE_TIME = "lastBackStoreTime";
    public static final String LAST_BACK_STORE_MILEAGE = "lastBackStoreMileage";
    public static final String DEALER_CODE = "dealerCode";
    public static final String NICK_NAME = "nickname";
    public static final String AID = "aid";
    public static final String ID = "id";
    public static final String MOBILE = "mobile";
    public static final String VEHICLE_SERIES_NAME = "vehicleSeriesName";
    public static final String VEHICLE_NUMBER = "vehicleNumber";
    public static final String PURCHASE_DATE = "purchaseDate";
    public static final String OTHER_NICK_NAME = "nickName";
    public static final String TENANTID_VW = TWO;
    public static final String DEALER_THREE = "03";
    public static final String TEMPLATE_CODE = "templateCode";
    public static final String TEMPLATE_NAME = "templateName";
    public static final String BRAND_ID = "brandId";
    public static final String NAME = "name";
    public static final String IDENTITY_CARD = "identityCard";
    public static final String STATUS = "status";
    public static final String REGION_DEALER = "regionDealer";
    public static final String BILL_TYPE = "billType";
    public static final String PART_CODE = "partCode";
    public static final String IS_REDEEM_CODE = "isRedeemCode";
    public static final String GENERATE_WAY = "generateWay";
    public static final String VALUE_TYPE = "valueType";
    public static final String VALUE_TXT = "valueTxt";
    public static final String STR_NULL = " ";
    public static final String VALIDDATE_TYPE = "validDateType";
    public static final String ENTITY_VALUE = "entityValue";
    public static final String DATE_PATTERN_END = " 00:00:00";
    public static final String REDEEM_START_TIME = "redeemStartTime";
    public static final String REDEEM_END_TIME = "redeemEndTime";
    public static final String VEHICLE_SERIES = "vehicle_series";
    public static final String SORT_ID = "_id";
    public static final String EXPORT_WORKBOOK_NAME = "优惠劵明细列表";
    public static final String VIN_CODE = "VIN码";
    public static final String PHONE = "手机号";
    public static final String URL = "url";
    public static final String FILE_SIZE = "fileSize";
    public static final String KB = "kb";
    public static final String PER_CENT = "%";
    private static final String SERIAL_NUMBER = "序号";
    private static final String ACCOUNT_TIME = "核销时间";
    private static final String VERIFICATION_NO = "工单号";
    private static final String REDEEMCODE = "核销码";
    private static final String VOUCHER_STATUS = "状态";
    private static final String ACTIVITY_NAME = "活动名称";
    private static final String MODE_OF_DISTRIBUTION = "发放方式";
    private static final String CUSTORMER = "客户";
    private static final String VEHICLE_MODEL = "车型";
    private static final String DISTRIBUTION_OF_SCENE = "发放场景";
    private static final String CARD_TICKET_NAME = "优惠劵名称";
    private static final String CARD_TICKET_TYPE = "优惠劵类型";
    private static final String SINGLE_SUPPORT_AMOUNT = "单次支持金额";
    private static final String BUSINESS_TYPE = "业务类型";
    private static final String ALREADY_RECEIVED = "已领取";
    private static final String ALREADY_USED = "已使用";

    public static final String[] DOWNLOAD_DEALER_TEMPLATE_TITLE = {SERIAL_NUMBER, "经销商销售代码",
        "经销商服务代码", "经销商名称"};
    public static final String[] DOWNLOAD_USER_VOUCHER_DETAIL_LIST_TITLE = {SERIAL_NUMBER, "发放大区",
        "发放小区", "发放方", ACTIVITY_NAME, "服务代码", CUSTORMER, PHONE, VEHICLE_MODEL, "车辆销售日期", VIN_CODE,
        "领取时间", "ACCOUNT_TIME", "过期时间", VERIFICATION_NO, REDEEMCODE, VOUCHER_STATUS, "核销大区", "核销小区",
        "核销店", "核销店服务代码", MODE_OF_DISTRIBUTION, DISTRIBUTION_OF_SCENE, CARD_TICKET_NAME,
        SINGLE_SUPPORT_AMOUNT};
    public static final String[] DOWNLOAD_USER_VOUCHER_DETAIL_LIST_FOR_DEALER_TITLE = {
        SERIAL_NUMBER, "劵批次号", ACTIVITY_NAME, CARD_TICKET_NAME, CARD_TICKET_TYPE, BUSINESS_TYPE,
        CUSTORMER, PHONE, VEHICLE_MODEL, VIN_CODE,
        "发放时间", ACCOUNT_TIME, REDEEMCODE, VERIFICATION_NO, VOUCHER_STATUS, "有效期", "券面金额（元）", "额度限制",
        "单次保养套餐原价（元）", SINGLE_SUPPORT_AMOUNT};
    public static final String[] DOWNLOAD_VOUCHER_BATCH_LIST_TITLE = {SERIAL_NUMBER, ACTIVITY_NAME,
        CARD_TICKET_NAME, CARD_TICKET_TYPE, BUSINESS_TYPE, "优惠劵批次号", "领取有效期", "发放数量", "库存", "劵面额",
        "实物价值", "消费额度限制",
        "有效期类型", "优惠劵使用有效期", "批次状态", MODE_OF_DISTRIBUTION, DISTRIBUTION_OF_SCENE, ALREADY_RECEIVED, ALREADY_USED,
        "已到期", "可叠加"};
    public static final String[] DOWNLOAD_DIRECTING_GRANTEE_RESULT_TITLE = {"VIN", "结果", "原因"};
    public static final String[] DOWNLOAD_THIRD_PART_CODE_TEMPLATE_TITLE = {SERIAL_NUMBER, "核销内容"};
}
/**
 * Revision history -------------------------------------------------------------------------
 * <p>
 * Date Author Note -------------------------------------------------------------------------
 * 2019-08-11 zhangyunjiao create
 */