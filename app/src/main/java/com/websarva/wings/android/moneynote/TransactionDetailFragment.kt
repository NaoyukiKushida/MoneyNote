package com.websarva.wings.android.moneynote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TransactionDetailFragment : Fragment() {

//    private lateinit var viewPager: ViewPager2
//    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transaction_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewPager = view.findViewById(R.id.viewPager)
//        tabLayout = view.findViewById(R.id.tabLayout)

        // ViewPager2にアダプターを設定
//        val adapter = TransactionDetailPagerAdapter(childFragmentManager, lifecycle)
//        viewPager.adapter = adapter

        // TabLayoutMediatorをアダプターが設定された後にアタッチする
//        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            // タブのテキストを設定
//            tab.text = "Tab $position"
//        }.attach()

//        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            tab.text = when (position) {
//                0 -> getString(R.string.transaction_details_tab)
//                1 -> getString(R.string.balance_tab)
//                else -> throw IllegalArgumentException("Invalid position: $position")
//            }
//        }.attach()
    }
}
