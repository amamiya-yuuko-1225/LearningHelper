package com.experiment.learinghelper.ui.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.experiment.learinghelper.R
import kotlinx.android.synthetic.main.fragment_me.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var meViewModel: MeViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        meViewModel =
            ViewModelProviders.of(this).get(MeViewModel::class.java)

        val view1 = inflater.inflate(R.layout.me_myquestions, null)
        val view2 = inflater.inflate(R.layout.me_message, null)
        val pageview = ArrayList<View>()

        pageview.add(view1)
        pageview.add(view2)
        val mPagerAdapter: PagerAdapter = object : PagerAdapter() {
            //获取当前窗体界面数
            override fun getCount(): Int {
                // TODO Auto-generated method stub
                return pageview.size
            }

            //判断是否由对象生成界面
            override fun isViewFromObject(arg0: View, arg1: Any): Boolean {
                // TODO Auto-generated method stub
                return arg0 === arg1
            }

            //使从ViewGroup中移出当前View
            override fun destroyItem(arg0: View, arg1: Int, arg2: Any) {
                (arg0 as ViewPager).removeView(pageview.get(arg1))
            }

            //返回一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
            override fun instantiateItem(arg0: View, arg1: Int): Any {
                (arg0 as ViewPager).addView(pageview.get(arg1))
                return pageview.get(arg1)
            }
        }
        //绑定适配器
        viewPager.adapter = mPagerAdapter
        //设置viewPager的初始界面为第一个界面
        viewPager.currentItem = 0

        me_question.setOnClickListener {
            viewPager.currentItem = 0
        }

        me_message.setOnClickListener {
            viewPager.currentItem = 1
        }

        return inflater.inflate(R.layout.fragment_me, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}