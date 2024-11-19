package com.example.motos.ui.gallery

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.motos.AccesorioActivity
import com.example.motos.databinding.FragmentGalleryBinding
class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val textView: TextView = binding.txtGallery
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//Párrafo de detalle
        val detalle: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc nisi dolor, dictum nec mauris et, convallis sollicitudin nisi. Cras eu elit lectus. Suspendisse non arcu in metus facilisis sollicitudin."
        var costo: String = "$175.00"
        var intent: Intent
        binding.imgAccesorio01.setOnClickListener {
            Toast.makeText(activity,"Moto zuzuki 250 $60000.00",
                Toast.LENGTH_SHORT).show()
            costo = "$600000.00"
            intent = Intent(activity, AccesorioActivity::class.java)
            intent.putExtra("detalle",detalle)
            intent.putExtra("costo", costo)
            intent.putExtra("numAccesorio", 1)
            startActivity(intent)
        }
        binding.imgAccesorio02.setOnClickListener {
            Toast.makeText(activity,"yamaha $175000.00",
                Toast.LENGTH_SHORT).show()
            costo = "$175000.00"
            intent = Intent(activity, AccesorioActivity::class.java)
            intent.putExtra("detalle",detalle)
            intent.putExtra("costo", costo)
            intent.putExtra("numAccesorio", 2)
            startActivity(intent)
        }
        binding.imgAccesorio03.setOnClickListener {
            Toast.makeText(activity,"Honda  $310005.50",
                Toast.LENGTH_SHORT).show()
            costo = "$310005.50"
            intent = Intent(activity, AccesorioActivity::class.java)
            intent.putExtra("detalle",detalle)
            intent.putExtra("costo", costo)
            intent.putExtra("numAccesorio", 3)
            startActivity(intent)
        }
        binding.imgAccesorio04.setOnClickListener {
            Toast.makeText(activity,"ktm $260000.80",
                Toast.LENGTH_SHORT).show()
            costo = "$260000.80"
            intent = Intent(activity, AccesorioActivity::class.java)
            intent.putExtra("detalle",detalle)
            intent.putExtra("costo", costo)
            intent.putExtra("numAccesorio", 4)
            startActivity(intent)
        }
        binding.imgAccesorio05.setOnClickListener {
            Toast.makeText(activity,"kahuazaki $630000.00",
                Toast.LENGTH_SHORT).show()
            costo = "$630000.00"
            intent = Intent(activity, AccesorioActivity::class.java)
            intent.putExtra("detalle",detalle)
            intent.putExtra("costo", costo)
            intent.putExtra("numAccesorio", 5)
            startActivity(intent)
        }

    }//onViewCreated
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }//onDestroyView
}//class



