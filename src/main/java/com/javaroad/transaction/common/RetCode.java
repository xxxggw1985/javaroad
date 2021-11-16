package com.javaroad.transaction.common;

/**
 * 响应码枚举
 */
public enum RetCode {

    //没有认证
    UNAUTHORIZED(401, "没有认证"),
    //拒绝访问
    FORBIDDEN(403, "拒绝访问"),
    // 接口不存在
    NOT_FOUND(404, "接口不存在"),

    // 实名检查
    REAL_NAME_CHECK(500, "当前操作需要实名认证，请先进行实名认证"),


    /**
     * 经纪人账号相关
     */
    OWNER_REGISTER(1000, "店东注册"),
    JOIN_DEPARTMENT(1001, "请先加入门店进行认证"),
    STATE_STOP_JOB(1002, "你已被停职，暂时不能使用APP的功能 请复职后继续使用。"),
    STATE_RESIGN(1003, "你已离职，暂时不能使用APP的功能,若情况不实，请联系所在门店；若情况属实，可以确认离开门店。"),
    STATE_FREEZE(1004, "你账号已被冻结; 暂时不能使用APP的功能。"),
    DPT_STATE_FREEZE(1005, "你所在门店已被冻结; 暂时不能使用APP的功能"),
    DEPT_STATE_UN_REVIEW(1006, "你所在门店审核未通过，暂时不能使用APP的功能"),
    AUTH_ACCOUNT_EXIST(1100, "该手机号码已注册"),
    AUTH_VERIFICATION_CODE_ERROR(1101, "验证码不正确"),
    AUTH_ACCOUNT_NOT_EXIST(1102, "账号未注册，请重新输入或注册"),
    AUTH_ACCOUNT_ERROR(1103, "账号或密码错误，请重新输入"),
    AGENT_NOT_EXIST(1104, "经纪人不存在或已注销"),
    AGENT_DEPARTMENT_EXIST(1105, "您已加入门店，请重新登录"),
    AUTH_DPT_CHECKING(1106, "门店认证审核中"),

    // 成功
    SUCCESS(2000, "成功"),

    // 一般错误提示
    Fail(2001, "一般错误提示"),

    // 成功-需要用户核实
    SUCCESS_CHECK(2002, "成功"),

    // token过期
    TOKEN_EXPIRE(2003, "token过期"),
    //Refresh token过期
    REFRESH_TOKEN_EXPIRE(2004, "Refresh token过期"),
    //没有权限访问
    API_FORBIDDEN(2005, "当前用户没有权限访问"),

    // 参数错误
    PARAM_EXCEPTION(4000, "参数错误"),

    // 缓存过期
    CACHE_EXCEPTION(4001, "缓存过期"),

    // 签名过期Signature expired
    SIGN_EXPIRED(4002, "签名过期"),

    // 签名错误
    SIGN_ERROR(4003, "签名错误"),

    // 验证码错误异常
    SMS_CODE_ERROR(4004, "验证码错误"),

    // 服务器内部错误
    INTERNAL_SERVER_ERROR(5000, "服务器内部错误"),

    //发票平台未登录
    INVOICE_NO_LOGIN(2009, "发票平台未登录"),

    //楼盘下架或已删除
    PROJECT_FAILURE(6000, "楼盘已失效"),


    //bbs相关
    //当前城市未开通
    BBS_NOT_OPEN(6500, "该功能暂未开通"),
    //没关注任何人
    BBS_NOT_FOLLOW(6501, "你还没关注任何人哦，快去广场逛逛吧"),
    BBS_NOT_FOLLOW_TOPIC(6502, "你还没关注任何话题哦，快去广场逛逛吧"),

    /*
     * 小程序相关
     */
    ACCESS_TOKEN_FAILURE(7000, "ACCESS_TOKEN获取失败，请稍侯再试"),

    /*
     * 二手房相关 8000开始
     */
    SH_PROJECT_EXIST(8000, "存在相同二手房源"),
    SH_PROJECT_HAS_CHECK(8001, "已存在相同地址房源，不可重复上传，若对房源信息疑问可联系客服"),
    NOT_IN_RECEIVE_TIME(8002, "当前是非认领时间，请在08:00~20:00再来查看吧"),
    OWNER_CHECK_FAIL(8003, "上传的身份信息与房本证上的业主信息不一致，请重新认证"),
    SH_LINKS_FAIL(8004, "该链接已失效"),
    SH_PROJECT_DEL(8005, "二手房不存在"),

    OWNER_CHECK_1(8101, "房源已完成了业主认证，此认证链接失效"),
    OWNER_CHECK_2(8102, "您已经完成了确认房源认证"),
    OWNER_CHECK_3(8103, "你选择了这不是您在卖的房子 并选择不需要客服介入"),
    OWNER_CHECK_4(8104, "你你选择了这不是您在卖的房子 客服正在核实房源信息，请耐心等待"),
    OWNER_CHECK_5(8105, "你选择了这不是您在卖的房子 经客服核实，房源处理结果为：XXXXXXXXX"),
    OWNER_CHECK_6(8106, "登录用户与委托用户不一致，请重新登录后再进行认证"),
    OWNER_CHECK_7(8107, "您已成功提交了业主认证，平台将于1-3个工作日内完成审核，请耐心等待"),

    ACTIVITY_OFF(9001, "活动已结束"),
    ACTIVITY_NOT_START(9002, "活动未开始"),

    END_OF_EVALUATION(9100, "评价已结束"),
    EVALUATED(9200, "已经评价，不能重复评价"),
    /**
     * 配盘相关
     */
    REQ_MATCH_EXIST(30001, "配盘选房单已存在"),
    REQ_MATCH_INSERT_FAIL(30002, "选房单保存失败"),
    REQ_MATCH_PROJECT_INSERT_FAIL(30003, "选房单楼盘关联表保存失败"),
    REQ_MATCH_NOT_FOUND(30004, "配盘选房单不存在"),

    //查看底价次数相关
    BASE_PRICE_VIEW_COUNT_NO_EXIST(31001, "该助力不存在"),
    BASE_PRICE_ASSIST_SELF(31002, "不能自己助力自己"),
    BASE_PRICE_ASSIST_EXIST(31003, "你今天已经助力过该好友了,请明天再试"),
    BASE_PRICE_VIEW_COUNT_ZERO(31004, "你的查看底价次数不够了哦"),

    //交易工具相关
    TRADING_TOOL_CITY_EXIST(32001, "关系已存在"),
    TRADING_TOOL_CITY_NO_EXIST(32002, "关系不存在"),
    TRADING_TOOL_NO_EXIST(32003, "交易工具不存在"),
    TRADING_TOOL_TYPE_EXIST(32004, "该城市该类型已存在"),
    TRADING_TOOL_QUESTION_OPTION_NO_EXIST(32005, "该工具下已经没有题目"),
    //交易工具相关
    AGENT_PROJECT_COLLECT_EXIST(33001, "已收藏过了"),
    AGENT_PROJECT_COLLECT_CANCELED(33002, "已经取消过了"),

    //抢单获客
    PROJECT_USER_REQ_BEFORE_START(34001, "还没开始"),
    PROJECT_USER_REQ_AFTER_END(34002, "已经结束"),
    PROJECT_USER_REQ_MAX_EVERYDAY(34003, "您今天已抢单超过次数"),
    PROJECT_USER_REQ_UNAVAILABLE(34004, "少侠手速太慢啦,该单已被其他人抢走啦"),
    PROJECT_USER_REQ_AGENT_EXIST(34005, "此单已经抢过啦"),
    PROJECT_USER_REQ_NO_EXIST(34006, "此单不存在"),


    //楼盘价格区间相关
    PROJECT_PRICE_RANG_LEFT_RIGHT_ERROR(35001, "右区间必须大于左区间"),
    PROJECT_PRICE_RANG_COUNT_ERROR(35002, "该城市价格区间记录数已超过系统设置"),
    PROJECT_PRICE_RANG_COUNT_NO_EXIST(35003, "区间不存在"),

    //经纪人资料相关
    AGENT_PROFILE_DONT_EXIST(36000, "经纪人不存在"),
    AGENT_PROFILE_QRCODE_FILE_EMPTY(36001, "二维码图片为空"),
    AGENT_PROFILE_QRCODE_FILE_SUFFIX_ERROR(36002, "二维码图片格式错误"),
    AGENT_PROFILE_QRCODE_FILE_ERROR(36003, "二维码图片不是微信二维码"),
    AGENT_PROFILE_QRCODE_DECODE_ERROR(36004, "图片识别二维码失败"),

    //商业区相关
    AGENT_AREA_BUSINESS_IS_EXIST(37001, "已添加过此商业区"),
    AGENT_AREA_BUSINESS_BEYOND_FIVE(37002, "商区只能设置5个哦"),
    //标签相关
    AGENT_TAG_BEYOND_SIX(38001, "标签只能设置6个哦"),
    //经纪人资料主营楼盘相关
    AGENT_PROFILE_PROJECT_IS_EXIST(39001, "已添加过此楼盘"),
    AGENT_PROFILE_PROJECT_BEYOND_FIVE(39002, "楼盘只能设置10个哦"),
    AGENT_PROFILE_PROJECT_IDS_IS_EXIST(39003, "传入楼盘Id不能为空"),

    //实名认证相关
    VERIFIED_NO_MATCH(40000, "实名失败，请检查姓名与身份证号是否匹配"),

    //门店相关
    DEPARTMENT_AGENT_MAXIMUM(60000, "超过门店最大经纪人限制"),


    NO_EXITS(0, "不存在"),
    EXITS(104, "已存在"),


    // 极光ResultCode
    J_PUSH_SUCCESS(200, "响应成功"),

    EXIST_DATA(39999, "该月份存在数据，是否覆盖");


    public int code;
    public String name;

    RetCode(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
