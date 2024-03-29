package com.robi.mdb.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.robi.mdb.databinding.DialogAlertBottonSheetBinding

class AlertButtonSheet: BottomSheetDialogFragment() {
    private var _binding: DialogAlertBottonSheetBinding? = null
    private val binding get() = _binding!!
    var title = ""
    var description = ""
    var background = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAlertBottonSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTitle.text = title
        binding.tvDescription.text = description
        if (background!=0) {
            binding.bt.setBackgroundColor(ContextCompat.getColor(requireContext(), background))
        }
    }
}