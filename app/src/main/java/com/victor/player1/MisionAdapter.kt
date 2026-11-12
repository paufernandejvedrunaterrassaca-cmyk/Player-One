package com.victor.player1

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.victor.player1.databinding.ItemMisionBinding

class MisionViewHolder(
    private val b: ItemMisionBinding
) : RecyclerView.ViewHolder(b.root) {

    fun bind(
        m: Mision,
        onClick: (Mision) -> Unit,
        onEdit: (Mision) -> Unit,
        onDelete: (Mision) -> Unit
    ) {
        val ctx = b.root.context

        b.tvEmoji.text = m.emoji
        b.tvTitol.text = m.titol
        b.tvDescripcio.text = m.descripcio

        b.tvProgres.text = ctx.getString(
            R.string.missio_progres_fmt,
            m.progres,
            m.objectiu
        )

        b.tvXp.text = ctx.getString(
            R.string.missions_recompensa_xp_fmt,
            m.xp
        )

        b.tvMonedes.text = ctx.getString(
            R.string.missions_recompensa_moneda_fmt,
            m.monedes
        )

        b.progressMissio.max = 100

        val percentatge = if (m.objectiu > 0) {
            (m.progres * 100 / m.objectiu).coerceIn(0, 100)
        } else {
            0
        }

        b.progressMissio.progress = percentatge

        b.progressMissio.contentDescription = ctx.getString(
            R.string.missio_progres_descripcio,
            m.progres,
            m.objectiu
        )

        val (textRes, colorRes) = when (m.estat) {
            Estat.PENDENT ->
                R.string.missions_estat_pendent to R.color.status_pending

            Estat.EN_PROGRES ->
                R.string.missions_estat_progres to R.color.status_in_progress

            Estat.COMPLETADA ->
                R.string.missions_estat_completada to R.color.status_completed
        }

        b.tvEstat.setText(textRes)
        b.tvEstat.setTextColor(
            ContextCompat.getColor(ctx, colorRes)
        )

        b.root.setOnClickListener {
            onClick(m)
        }

        if (m.creadaPerUsuari) {
            b.btnMissionOptions.visibility = View.VISIBLE

            b.btnMissionOptions.setOnClickListener { view ->
                val popup = PopupMenu(ctx, view)

                popup.menu.add(
                    0,
                    1,
                    0,
                    ctx.getString(R.string.missio_editar)
                )

                popup.menu.add(
                    0,
                    2,
                    1,
                    ctx.getString(R.string.missio_eliminar)
                )

                popup.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        1 -> {
                            onEdit(m)
                            true
                        }

                        2 -> {
                            onDelete(m)
                            true
                        }

                        else -> false
                    }
                }

                popup.show()
            }
        } else {
            b.btnMissionOptions.visibility = View.GONE
            b.btnMissionOptions.setOnClickListener(null)
        }
    }
}

class MisionAdapter(
    private val misions: MutableList<Mision>,
    private val mostrarNomésCreades: Boolean = false,
    private val onEdit: (Mision) -> Unit = {},
    private val onDelete: (Mision) -> Unit = {},
    private val onClick: (Mision) -> Unit
) : RecyclerView.Adapter<MisionViewHolder>() {

    private fun obtenirMissionsVisibles(): List<Mision> {
        return if (mostrarNomésCreades) {
            misions.filter { it.creadaPerUsuari }
        } else {
            misions
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MisionViewHolder {
        val binding = ItemMisionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MisionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return obtenirMissionsVisibles().size
    }

    override fun onBindViewHolder(
        holder: MisionViewHolder,
        position: Int
    ) {
        holder.bind(
            obtenirMissionsVisibles()[position],
            onClick,
            onEdit,
            onDelete
        )
    }

    fun add(m: Mision) {
        val novaMision = m.copy(creadaPerUsuari = true)

        misions.add(novaMision)
        notifyDataSetChanged()

        Log.d("MisionAdapter", "Afegida: ${novaMision.titol}")
    }

    fun update(pos: Int, m: Mision) {
        val missionsVisibles = obtenirMissionsVisibles()

        if (pos !in missionsVisibles.indices) return

        val misionAntiga = missionsVisibles[pos]

        if (!misionAntiga.creadaPerUsuari) return

        val indexReal = misions.indexOfFirst {
            it.id == misionAntiga.id
        }

        if (indexReal == -1) return

        misions[indexReal] = m.copy(
            id = misionAntiga.id,
            creadaPerUsuari = true
        )

        notifyDataSetChanged()

        Log.d("MisionAdapter", "Modificada: ${m.titol}")
    }

    fun removeAt(pos: Int) {
        val missionsVisibles = obtenirMissionsVisibles()

        if (pos !in missionsVisibles.indices) return

        val mision = missionsVisibles[pos]

        if (!mision.creadaPerUsuari) return

        val indexReal = misions.indexOfFirst {
            it.id == mision.id
        }

        if (indexReal == -1) return

        val m = misions.removeAt(indexReal)

        notifyDataSetChanged()

        Log.d("MisionAdapter", "Eliminada: ${m.titol}")
    }
}
