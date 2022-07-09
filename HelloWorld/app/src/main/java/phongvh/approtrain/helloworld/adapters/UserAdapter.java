package phongvh.approtrain.helloworld.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import phongvh.approtrain.helloworld.R;
import phongvh.approtrain.helloworld.modals.User;

public class UserAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<User> mArrayList;
    public UserAdapter(Context context, ArrayList<User> arrayList) {
        this.mContext = context;
        this.mArrayList = arrayList;
    }


    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public User getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View converView, ViewGroup viewGroup) {
        converView = LayoutInflater.from(mContext).inflate(R.layout.item_user, viewGroup, false);
        TextView tvUserName = (TextView) converView.findViewById(R.id.tvUserName);
        TextView tvFullName = (TextView) converView.findViewById(R.id.tvFullName);
        User user = getItem(position);
        tvUserName.setText(user.getUserName());
        tvFullName.setText(user.getFullName());
        return converView;
    }
}
