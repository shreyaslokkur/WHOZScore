package com.lks.whozscore.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import com.lks.whozscore.R;
import com.lks.whozscore.core.PatientInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 5/28/15
 * Time: 6:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResultCardArrayAdapter extends ArrayAdapter<ResultCard> {

    private static final String TAG = "CardArrayAdapter";
    private List<ResultCard> cardList = new ArrayList<ResultCard>();
    private PatientInterface patientInterface;

    static class CardViewHolder {
        TextView header;
        TextView zScoreResult;
        ImageButton graph;
        ImageButton info;
    }

    public ResultCardArrayAdapter(Context context, int textViewResourceId, PatientInterface patientInterface) {
        super(context, textViewResourceId);
        this.patientInterface = patientInterface;
    }

    @Override
    public void add(ResultCard object) {
        cardList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.cardList.size();
    }

    @Override
    public ResultCard getItem(int index) {
        return this.cardList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CardViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.result_list_item_card, parent, false);
            viewHolder = new CardViewHolder();
            viewHolder.header = (TextView) row.findViewById(R.id.header);
            viewHolder.zScoreResult = (TextView) row.findViewById(R.id.zScoreResultTextId);
            viewHolder.graph = (ImageButton) row.findViewById(R.id.graph);
            viewHolder.info = (ImageButton) row.findViewById(R.id.info);
            row.setTag(viewHolder);
        } else {
            viewHolder = (CardViewHolder)row.getTag();
        }
        ResultCard card = getItem(position);
        viewHolder.header.setText(card.getHeader());
        viewHolder.zScoreResult.setText(card.getzScoreResult());
        viewHolder.graph.setOnClickListener(new GraphButtonClickedListener(card.getzScoreCalculators(), patientInterface));
        viewHolder.info.setOnClickListener(new InfoButtonClickedListener(card.getzScoreCalculators(),patientInterface));
        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
