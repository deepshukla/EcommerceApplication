package com.lex.ecommerceapplication.util;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * activity Util class to replace fragment.
 *
 * @author DeepS
 */
public class ActivityUtil
{

    public static void replaceFragment(AppCompatActivity activity, Fragment fromFragment, Fragment toFragment, boolean addToBackStack, int frameId)
    {
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(frameId, toFragment, toFragment.getClass().getName());
        if (addToBackStack && (fromFragment != null))
        {
            ft.addToBackStack(fromFragment.getClass().getName()); // we are following standard practice of giving fromFrag class name as name for the back stack state.
        }

        ft.commitAllowingStateLoss();
    }

}
