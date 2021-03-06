package com.wwdablu.soumya.lottiebottomnav;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.wwdablu.soumya.lottiebottomnav.databinding.LottieMenuItemBinding;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

final class LottieViewCreator {

    @NonNull
    static LottieMenuItemBinding from(@NonNull ViewGroup parent,
                                      @NonNull MenuItem menuItem,
                                      boolean  isSelected,
                                      @NonNull Config config) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        LottieMenuItemBinding binder = DataBindingUtil.inflate(inflater, R.layout.lottie_menu_item, parent, false);

        binder.lmiMenuText.setText(menuItem.menuTitle);
        binder.lmiMenuText.setTextColor(isSelected ? menuItem.menuTextSelectedColor : menuItem.menuTextUnselectedColor);

        setLottieView(binder.lmiMenuItem, menuItem, isSelected);

        ViewGroup.LayoutParams params = binder.lmiMenuItem.getLayoutParams();
        params.width = isSelected ? config.getSelectedMenuWidth() : config.getUnselectedMenuWidth();
        params.height = isSelected ? config.getSelectedMenuHeight() : config.getUnselectedMenuHeight();
        binder.lmiMenuItem.setLayoutParams(params);

        if(!config.isShowTextOnUnselected()) {
            binder.lmiMenuText.setVisibility(isSelected ? View.VISIBLE : View.INVISIBLE);
        }

        return binder;
    }

    private static void setLottieView(LottieAnimationView view, MenuItem menuItem, boolean isSelected) {

        switch (menuItem.lottieSource) {

            case Raw:
            case Assets:
                view.setAnimation(isSelected ? menuItem.selectedLottieName : menuItem.unselectedLottieName);
                view.pauseAnimation();
                view.setProgress(menuItem.lottieProgress);
        }
    }
}
