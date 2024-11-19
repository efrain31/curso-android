package com.example.appmenulateral2.ui.home;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.appmenulateral2.R;
import com.example.appmenulateral2.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflar el layout usando ViewBinding
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Referencia al TextView
        final TextView textView = binding.textHome;

        // Establecer el texto largo desde strings.xml
        textView.setText(getString(R.string.nosotros));

        // Habilitar scrolling para el TextView en caso de texto extenso
        textView.setMovementMethod(new ScrollingMovementMethod());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
