package com.muan.takeout.Activity;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.muan.takeout.Adapter.SearchResultAdapter;
import com.muan.takeout.Model.GoodsEntity;
import com.muan.takeout.R;
import com.muan.takeout.Utils.MessageUtils;
import com.muan.takeout.Utils.PreferenceUtils;
import com.muan.takeout.Widget.tagview.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ${Muan} on 2016/11/1 13:57
 */
public class SearchActivity extends BaseActivity {
    @BindView(R.id.iv_search_back)
    public ImageView mIv_back;
    @BindView(R.id.et_search_content)
    public EditText mEt_searchcontent;//编辑框
    @BindView(R.id.ll_search_content)
    public LinearLayout mLayoutContent;//编辑框父控件
    @BindView(R.id.flow_search_tags)
    public FlowLayout mFlow_tags;//推荐标签组
    @BindView(R.id.iv_search_clear)
    public ImageView mIv_clear;//清除历史
    @BindView(R.id.ll_search_tags)
    public LinearLayout mLl_tags;//标签组
    @BindView(R.id.fram_tags)
    public FrameLayout mFrame_tags;//搜索标签组
    @BindView(R.id.lv_searchresult)
    public ListView mListView_reslut;//搜索结果

    @BindView(R.id.flow_search_history)
    public FlowLayout flow_search_history;//历史标签组
    private final int HISTORY_MAX_LENGTH = 15;//搜索历史最大长度
    private final String HISTORY_SPILT_TAG = "￥";//历史记录分割符
    private List<String> mSearchHistoryList;//搜索历史
    private List<String> mSearchRecomList;//推荐词组
    private List<GoodsEntity> mList_search;

    @BindView(R.id.ll_searchresult)
    public LinearLayout mLayout_searchresult;
    @BindView(R.id.tv_allsearch)
    public TextView mTv_allsearch;
    @BindView(R.id.tv_buyall)
    public TextView mTv_buyall;
    SearchResultAdapter mAdapter;


    @Override
    public void initData() {
        mSearchHistoryList = new ArrayList<>();
        mSearchRecomList = new ArrayList<>();
        mList_search = new ArrayList<>();
        mAdapter = new SearchResultAdapter(mList_search, this);
        mListView_reslut.setAdapter(mAdapter);
        initTestData();

    }

    private void initTestData() {
        getTagData();
        getSearchHistory();
    }

    private void getTagData() {
        String[] mRecomList = {"苹果7", "苹果6", "苹果5"};
        mFlow_tags.removeAllViews();
        if (mRecomList != null && mRecomList.length > 0) {
            int size = mRecomList.length;
            for (int i = 0; i < size && i < 10; i++) {
                mFlow_tags.addView(getTagTextView(i, mRecomList[i]));
            }
        }
    }

    @Override
    public void initView() {
        mEt_searchcontent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mLl_tags.setVisibility(View.VISIBLE);
                    mFrame_tags.setVisibility(View.VISIBLE);
                    mLayout_searchresult.setVisibility(View.GONE);
                } else {
                    mLl_tags.setVisibility(View.GONE);
                    mEt_searchcontent.setText("");
                    imm.hideSoftInputFromInputMethod(mEt_searchcontent.getWindowToken(), 0);
                }
            }
        });
        mEt_searchcontent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND ||
                        (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) ||
                        (event != null && event.getKeyCode() == KeyEvent.KEYCODE_SEARCH)) {
                    add2History(mEt_searchcontent.getText() + "");
//                    Intent intent = new Intent(mActivity, SearchResultActivity.class);
//                    intent.putExtra("title", mEt_searchcontent.getText() + "");
//                    startActivity(intent);
                    getSearchResult(mEt_searchcontent.getText() + "");
                    return false;
                }
                return false;
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_search;
    }

    @OnClick({R.id.iv_search_back, R.id.iv_search_clear, R.id.tv_buyall, R.id.et_search_content, R.id.ll_search_content})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search_back:
                this.finish();
                break;
            case R.id.iv_search_clear:
                PreferenceUtils.getInstance(getApplicationContext()).saveString(PreferenceUtils.SEARCH_HISTORY, "");
                mSearchHistoryList.clear();
                showHistory();
                break;
            case R.id.tv_buyall:
                MessageUtils.alertLongMessageCENTER("全部加入购物车");
                break;
            case R.id.et_search_content:
            case R.id.ll_search_content:
                mEt_searchcontent.requestFocus();
                break;
        }
    }

    /**
     * 拿搜索历史
     */
    private void getSearchHistory() {
        String history = PreferenceUtils.getInstance(getApplicationContext()).getString(PreferenceUtils.SEARCH_HISTORY, "");
        String[] historyList = history.split(HISTORY_SPILT_TAG);
        int length = historyList.length;
        if (length > 0) {
            mSearchHistoryList = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                if (!TextUtils.isEmpty(historyList[i]))
                    mSearchHistoryList.add(historyList[i]);
            }
        }
        showHistory();

    }

    /**
     * 显示搜搜历史
     */
    private void showHistory() {
        flow_search_history.removeAllViews();
        if (mSearchHistoryList != null && mSearchHistoryList.size() > 0) {
            int size = mSearchHistoryList.size();
            for (int i = 0; i < size && i < 10; i++) {
                flow_search_history.addView(getTagTextView(i, mSearchHistoryList.get(i)));
            }
        }
    }

    /**
     * 设置标签样式 属性
     *
     * @param i
     * @param searchTag
     * @return
     */
    private TextView getTagTextView(int i, String searchTag) {
        TextView tv = new TextView(mActivity);
        tv.setBackgroundResource(R.drawable.shape_tag);
        tv.setPadding(40, 10, 40, 10);
        tv.setTextColor(Color.parseColor("#666666"));
        tv.setText(searchTag);
        tv.setTag(searchTag);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEt_searchcontent.setText(v.getTag() + "");
                add2History(v.getTag() + "");
                mEt_searchcontent.setSelection(mEt_searchcontent.length());

                getSearchResult(v.getTag() + "");
            }
        });
        return tv;
    }

    /**
     * 添加到搜索历史
     *
     * @param searchString
     */
    private void add2History(String searchString) {
        if (mSearchHistoryList == null) {
            mSearchHistoryList = new ArrayList<>();
        }
        if (mSearchHistoryList.contains(searchString)) {
            mSearchHistoryList.remove(searchString);
        }
        mSearchHistoryList.add(0, searchString);
        if (mSearchHistoryList.size() > HISTORY_MAX_LENGTH) {
            mSearchHistoryList.remove(mSearchHistoryList.size() - 1);
        }
        showHistory();
        int size = mSearchHistoryList.size();
        StringBuffer searchHostoryStr = new StringBuffer("");
        for (int i = 0; i < size; i++) {
            searchHostoryStr.append(mSearchHistoryList.get(i) + HISTORY_SPILT_TAG);
        }
        PreferenceUtils.getInstance(getApplicationContext()).saveString(PreferenceUtils.SEARCH_HISTORY, searchHostoryStr.toString());
    }

    /**
     * 通过关键字搜索商品
     *
     * @param keyword 标签或查询框输入内容
     */
    private void getSearchResult(String keyword) {
        mFrame_tags.setVisibility(View.GONE);
        mLayout_searchresult.setVisibility(View.VISIBLE);
        //商品数据
        for (int i = 0; i < 20; i++) {
            GoodsEntity good = new GoodsEntity();
            good.setImg("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=ipone7%E5%9B%BE%E7%89%87&step_word=&hs=0&pn=0&spn=0&di=35616351210&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=3256437698%2C2740429284&os=2904359539%2C2437687918&simid=3449594756%2C359644484&adpicid=0&ln=1850&fr=&fmq=1477968599095_R&fm=result&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fi2.qihuiwang.com%2Fweb%2F2015-02-28%2F0149075f13ca32b6b4.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fgjof_z%26e3Bqti7towg2_z%26e3Bv54AzdH3FEq7tr4jgpAzdH3Fda8caddblmccl_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0");
            good.setTotal(7000);
            good.setRemain(i * 30);
            good.setTitle("iphone7测试" + i);
            mList_search.add(good);
        }
        mAdapter.notifyDataSetChanged();


    }
}
