package xit.zubrein.demo.adapter

import android.content.Context
import android.widget.Toast
import xit.zubrein.demo.R
import xit.zubrein.demo.base.BaseRecyclerviewAdapter
import xit.zubrein.demo.databinding.ScheduleItemBinding
import xit.zubrein.demo.listener.SchedulerDeleteCallBack
import xit.zubrein.demo.model.ModelSchedule
import java.util.*

class SchedulerAdapter(
    private val context: Context,
    private val callBack: SchedulerDeleteCallBack
) :
    BaseRecyclerviewAdapter<ModelSchedule, ScheduleItemBinding>() {

    override fun getLayout() = R.layout.schedule_item

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<ScheduleItemBinding>,
        position: Int
    ) {
        holder.binding.apply {
            val item = items[position]
            model = item
        }

        holder.binding.delete.setOnClickListener {
            callBack.deleteData(items[position])
        }


        if (Calendar.getInstance().timeInMillis > items[position].scheduleTime) {
            holder.binding.status.text = "Executed"
        }else{
            if (items[position].status) {
                holder.binding.status.text = "ACTIVE"
            } else {
                holder.binding.status.text = "NOT ACTIVE"
            }
        }

        holder.binding.status.setOnClickListener {
            if (Calendar.getInstance().timeInMillis < items[position].scheduleTime) {
                if (items[position].status) {
                    callBack.inactiveSchedule(items[position])
                } else {
                    callBack.activeSchedule(items[position])
                }
            } else {
                Toast.makeText(context, "This schedule has expired", Toast.LENGTH_SHORT).show()
            }

        }

        holder.binding.edit.setOnClickListener {
            callBack.updateData(items[position])
        }

        val drawable = context.packageManager.getApplicationIcon(items[position].packageName)

        holder.binding.appLogo.setImageDrawable(drawable)


    }
}
