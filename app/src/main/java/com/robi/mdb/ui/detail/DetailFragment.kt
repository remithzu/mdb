package com.robi.mdb.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.robi.mdb.R
import com.robi.mdb.databinding.FragmentDetailBinding
import com.robi.mdb.databinding.ItemActorBinding
import com.robi.mdb.databinding.ItemProductionBinding
import com.robi.mdb.models.Actor
import com.robi.mdb.models.MovieDetail
import com.robi.mdb.models.Result
import com.robi.mdb.networks.NetworkState
import com.robi.mdb.ui.dialog.AlertButtonSheet
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment: Fragment() {
    val viewModel by viewModel<DetailViewModel>()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: Adapter
    private lateinit var adapter2: Adapter2
    private var movieId = 0
    private var trailerUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = Adapter()
        adapter2 = Adapter2()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        movieId = arguments?.getInt("movie_id")?: 1212073
        adapter.actionListener = object: Adapter.OnActionListener {
            override fun onAction(result: Result) {
                TODO("Not yet implemented")
            }
        }
        binding.rvActorList.adapter = adapter
        adapter.notifyDataSetChanged()

        binding.rvProductionList.adapter = adapter2
        adapter2.notifyDataSetChanged()

        viewModel.getDetailMovie(movieId)
//        viewModel.getActor(movieId)
//        viewModel.getTrailer(movieId)

        binding.tvPlayTrailer.setOnClickListener {
            if (trailerUrl!=null) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(trailerUrl)
                    )
                )
            } else {
                val modalBottomSheet = AlertButtonSheet()
                modalBottomSheet.title = "Ops..."
                modalBottomSheet.description = "Trailer not found!"
                modalBottomSheet.background = R.color.red
                modalBottomSheet.show(childFragmentManager, "LocationBottomSheet")
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    private fun setupViewModel() {
        viewModel.movieDetail.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkState.Loading -> {
                }
                is NetworkState.Success -> {
                    val d = it.data
                    if (d!=null) {
                        binding.apply {
                            Glide.with(requireContext())
                                .asDrawable()
                                .load("https://image.tmdb.org/t/p/w185${d.posterPath}")
                                .into(ivCover)
                            Glide.with(requireContext())
                                .asDrawable()
                                .load("https://image.tmdb.org/t/p/w300${d.backdropPath}")
                                .into(ivCoverOverlay)
                            tvUserScore.text = "${d.voteAverage}/10"
                            tvTitle.text = d.title
                            tvReleaseStatus.text = d.status
                            tvReleaseDate.text = d.releaseDate
                            tvGenre.text = d.genres.joinToString { g -> g.name }
                            tvTagLine.text = d.tagline
                            tvDescription.text = d.overview
                            adapter2.list = d.productionCompanies
                            adapter2.notifyDataSetChanged()
                        }
                    }
                }
                else -> {
                }
            }
        }

        viewModel.movieActor.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkState.Loading -> {
                }
                is NetworkState.Success -> {
                    val d = it.data
                    if (d!=null) {
                        adapter.list = d.cast
                        adapter.notifyDataSetChanged()
                    }
                }
                else -> {
                }
            }
        }

        viewModel.movieTrailer.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkState.Loading -> {
                }
                is NetworkState.Success -> {
                    if (it.data!=null) {
                        trailerUrl = it.data
                    }
                }
                else -> {
                }
            }
        }
    }

    class ViewHolder(val bindItem: ItemActorBinding): RecyclerView.ViewHolder(bindItem.root)
    class Adapter: RecyclerView.Adapter<ViewHolder>() {
        var list = listOf<Actor.Cast?>()
        var actionListener: OnActionListener? = null

        interface OnActionListener {
            fun onAction(result: Result)
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {
            return ViewHolder(
                ItemActorBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(
            holder: ViewHolder,
            position: Int
        ) {
            val m = list[position]!!
            Glide.with(holder.itemView.context)
                .asDrawable()
                .load("https://image.tmdb.org/t/p/w45${m.profilePath}")
                .placeholder(ContextCompat.getDrawable(holder.itemView.context, R.drawable.ic_box))
                .into(holder.bindItem.ivCover)
            holder.bindItem.tvCharacter.text = m.character
            holder.bindItem.tvName.text = m.originalName
        }
    }

    class ViewHolder2(val bindItem: ItemProductionBinding): RecyclerView.ViewHolder(bindItem.root)
    class Adapter2: RecyclerView.Adapter<ViewHolder2>() {
        var list = listOf<MovieDetail.ProductionCompany?>()

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder2 {
            return ViewHolder2(
                ItemProductionBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(
            holder: ViewHolder2,
            position: Int
        ) {
            val m = list[position]!!
            holder.bindItem.tvTitle.text = m.name
            holder.bindItem.tvSubtitle.text = m.originCountry
        }
    }
}