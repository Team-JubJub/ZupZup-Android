package com.example.zupzup.ui.fragment.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.zupzup.databinding.FragmentTermsDetailDialogBinding

class TermsDetailDialogFragment : DialogFragment() {

    private var _binding: FragmentTermsDetailDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTermsDetailDialogBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTermsDetail.movementMethod = ScrollingMovementMethod()
        binding.tvTermsDetail.text =
            "<개인정보 수집 동의>\n" +
                    "\n" +
                    "1. 기본수집항목: 필수 (휴대)전화번호, 방문예정시간 [선택] 이름\n" +
                    "\n" +
                    "2. 수집 및 이용목적 : 사업자회원과 예약이용자의 원활한 거래 진행, 고객상담, 불만처리 등 민원 처리, 분쟁조정 해결을 위한 기록보존\n" +
                    "\n" +
                    "3. 보관기간\n" +
                    "\n" +
                    "서비스 운영 종료 후 파기\n" +
                    "\n" +
                    "단, 관련 법령에 의하여 일정 기간 보관이 필요한 경우에는 해당 기간 동안 보관함\n" +
                    "\n" +
                    "4. 동의 거부권 등에 대한 고지: 정보주체는 개인정보의 수집 및 이용 동의를 거부할 권리가 있으나, 이 경우 상품 및 서비스 예약이 제한될 수 있습니다.\n" +
                    "\n" +
                    "<개인정보 제공 동의>\n" +
                    "\n" +
                    "1. 개인정보를 제공받는 자 : 줍줍\n" +
                    "\n" +
                    "2. 제공하는 기본 개인정보 항목: 필수(휴대)전화번호, 방문예정시간 [선택] 이름\n" +
                    "\n" +
                    "3. 개인정보를 제공받는 자의 이용목적 : 사업자회원과 예약이용자의 원활한 거래 진행, 서비스 혜택 및 맞춤 서비스 제공, 민원처리 등 고객상담, 고객관리, 서비스 이용에 따른 설문조사 및 혜택 제공, 분쟁조정을 위한 기록보존\n" +
                    "\n" +
                    "4. 개인정보를 제공받는 자의 개인정보 보유 및 이용기간 : 서비스 운영 종료시 또는 위 개인정보 이용목적 달성 시 까지 이용합니다.\n" +
                    "\n" +
                    "5. 동의 거부권 등에 대한 고지 : 정보주체는 개인정보 제공 동의를 거부할 권리가 있으나, 이 경우 상품 및 서비스 예약이 제한될 수 있습니다."
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}