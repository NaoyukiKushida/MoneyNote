package com.websarva.wings.android.moneynote

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class TransactionDetailPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        // ページ数を返す
        return 1
    }

    override fun createFragment(position: Int): Fragment {
        // 各ページのFragmentを返す
        return TransactionDetailFragment()
    }

}
