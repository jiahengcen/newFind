package com.pwc.newfind

import android.content.ComponentName
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.pwc.swipemenulistview.*
import kotlinx.android.synthetic.main.company_favourite_activity.*

/**
 * Created by lhuang126 on 1/14/2018.
 */
class FavouriteCompanyActivity : AppCompatActivity() {
    private val mListView by lazy {
        findViewById<SwipeMenuListView>(R.id.listView);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //添加Flag把状态栏设为可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //设置状态栏颜色
        window.statusBarColor = Color.rgb(0xC5, 0x2A, 0x1A)
        setContentView(R.layout.company_favourite_activity)
        setSupportActionBar(toolbar)
        val listAdapter = ListAdapter()
        listAdapter.mAppList = Test.getStrings() as MutableList<String>
        mListView.adapter = listAdapter
        val creator = object : SwipeMenuCreator {
            override fun create(menu: SwipeMenu?) {
                // create "open" item
                val openItem = SwipeMenuItem(applicationContext)
                // set item background
                openItem.background = ColorDrawable(Color.rgb(0xE0, 0x30, 0x1E))
                // set item width
                openItem.width = dp2px(90);
                // set item title
                openItem.title = "取消关注";
                // set item title fontsize
                openItem.titleSize = 18;
                // set item title font color
                openItem.titleColor = Color.WHITE
                // add to menu
                menu!!.addMenuItem(openItem);
            }
        }
        // set creator
        mListView.setMenuCreator(creator)
        // step 2. listener item click event
        mListView.setOnMenuItemClickListener { position, menu, index ->
            val item = listAdapter.getItem(position)
            when (index) {
                0 -> {
                    // open
                    // open(item as ApplicationInfo)
                    listAdapter.notifyDataSetChanged()
                }

                1 -> {
                    // delete
                    //					delete(item);
                    //  listAdapter.removeAt(position)
                    //
                }
            }
            false
        }

        // set SwipeListener
        mListView.setOnSwipeListener(object : SwipeMenuListView.OnSwipeListener {

            override fun onSwipeStart(position: Int) {
                // swipe start
            }

            override fun onSwipeEnd(position: Int) {
                // swipe end
            }
        })

        // set MenuStateChangeListener
        mListView.setOnMenuStateChangeListener(object : SwipeMenuListView.OnMenuStateChangeListener {
            override fun onMenuOpen(position: Int) {}

            override fun onMenuClose(position: Int) {}
        })

        // other setting
//		listView.setCloseInterpolator(new BounceInterpolator());

        // test item long click
        mListView.onItemLongClickListener = AdapterView.OnItemLongClickListener { parent, view, position, id ->
            Toast.makeText(applicationContext, position.toString() + " long click", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun open(item: ApplicationInfo) {
        // open app
        val resolveIntent = Intent(Intent.ACTION_MAIN, null)
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        resolveIntent.`package` = item.packageName
        val resolveInfoList = packageManager
                .queryIntentActivities(resolveIntent, 0)
        if (resolveInfoList != null && resolveInfoList.size > 0) {
            val resolveInfo = resolveInfoList[0]
            val activityPackageName = resolveInfo.activityInfo.packageName
            val className = resolveInfo.activityInfo.name

            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            val componentName = ComponentName(
                    activityPackageName, className)

            intent.component = componentName
            startActivity(intent)
        }
    }

    private fun dp2px(dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(),
                resources.displayMetrics).toInt()
    }

    class ListAdapter : BaseSwipListAdapter() {
        var mAppList: MutableList<String>? = null
        override fun getCount(): Int {
            return mAppList!!.size
        }

        override fun getItem(position: Int): String {
            return mAppList!![position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = convertView
            if (convertView == null) {
                convertView = View.inflate(parent.context,
                        R.layout.company_favoirite_item_list_app, null)
                ViewHolder(convertView)
            }
            val holder = convertView!!.tag as ViewHolder
            val item = getItem(position)
            //holder.iv_icon.setImageDrawable(item.loadIcon(getPackageManager()))
            //holder.tv_name.text = item.loadLabel(getPackageManager())
            //holder.iv_icon.setOnClickListener { Toast.makeText(this@SimpleActivity, "iv_icon_click", Toast.LENGTH_SHORT).show() }
            //holder.tv_name.setOnClickListener { Toast.makeText(this@SimpleActivity, "iv_icon_click", Toast.LENGTH_SHORT).show() }
            return convertView
        }

        internal inner class ViewHolder(view: View) {
            var iv_icon: ImageView
            var tv_name: TextView

            init {
                iv_icon = view.findViewById<View>(R.id.iv_icon) as ImageView
                tv_name = view.findViewById<View>(R.id.tv_name) as TextView
                view.tag = this
            }
        }

        override fun getSwipEnableByPosition(position: Int): Boolean {
            return true
        }
    }
}




