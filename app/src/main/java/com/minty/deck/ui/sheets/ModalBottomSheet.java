package com.minty.deck.ui.sheets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.minty.deck.R;

public class ModalBottomSheet extends BottomSheetDialogFragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.bottom_sheet, container, false);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
