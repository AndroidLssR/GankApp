package me.lsran.gankapp.ui;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

import me.lsran.gankapp.internal.di.HasComponent;
import me.lsran.gankapp.navigator.Navigator;


/**
 * All fragments should extends this
 */
public abstract class BaseFragment extends Fragment {

    protected Navigator getNavigator() {
        if (getActivity() instanceof BaseActivity) {
            return ((BaseActivity) getActivity()).navigator;
        } else {
            return new Navigator();
        }
    }

    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
