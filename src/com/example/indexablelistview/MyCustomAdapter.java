package com.example.indexablelistview;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.AbsListView.SelectionBoundsAdjuster;
import android.widget.Button;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class MyCustomAdapter extends BaseAdapter implements SectionIndexer{

	private String mSections = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private Context mContext;
	ArrayList<MyDataClass> wordList;
	private LayoutInflater mInflater;

	public MyCustomAdapter(ArrayList<MyDataClass> wordList, Context mContext) {
		super();
		this.wordList = wordList;
		this.mContext = mContext;
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		if (wordList != null)
			return wordList.size();
		else
			return 0;
	}

	@Override
	public MyDataClass getItem(int position) {
		if (wordList != null)
			return wordList.get(position);
		else
			return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void onItemSelected(int position) {

	}

	@Override
	public View getView(final int position, View convertView,
			ViewGroup parent) {

		final int p = position;
		ViewHolder holder;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item_layout, null);
			holder = new ViewHolder();
			holder.wordmeName = (TextView) convertView
					.findViewById(R.id.wordName);
			holder.wordMeaning = (TextView) convertView
					.findViewById(R.id.wordMeaning);
		
			convertView.setTag(holder);

		}

		else {
			holder = (ViewHolder) convertView.getTag();
		}
		

		final String english_word =getItem(position).getEnglish_word().trim();
		holder.wordmeName.setText(english_word.toString().trim());
		
		//Correct bangla disply problem
		String wordmeaning_bangla=getItem(position).getNative_word().trim();
		
		holder.wordMeaning.setText(wordmeaning_bangla);

	    
	
		return convertView;
	}




	@Override
	public int getPositionForSection(int section) {
		for (int j = 0; j < getCount(); j++) {
			if (section == 0) {
				for (int k = 0; k <= 9; k++) {
					String text = null;
					try {
						text = wordList.get(j).getEnglish_word();
					} catch (Exception e) {
					}
					if (text == null)
						return 0;
					else if (String
							.valueOf(text.charAt(0))
							.toLowerCase()
							.equals(String.valueOf(String.valueOf(k))
									.toString().toLowerCase()))
						return j;
				}
			} else {
				String artist = null;
				try {
					artist = wordList.get(j).getEnglish_word();
				} catch (Exception e) {
				}
				if (artist == null)
					return 0;
				else if (String
						.valueOf(artist.charAt(0))
						.toLowerCase()
						.equals(String.valueOf(mSections.charAt(section))
								.toString().toLowerCase())) {
					return j;
				}
			}
		}
		return 0;
	}

	@Override
	public int getSectionForPosition(int position) {
		return 0;
	}

	@Override
	public Object[] getSections() {
		String[] sections = new String[mSections.length()];
		for (int i = 0; i < mSections.length(); i++)
			sections[i] = String.valueOf(mSections.charAt(i));
		return sections;
	}
}



class ViewHolder {
TextView wordmeName;
TextView wordMeaning;
TextView translitaration;
Button mp3;
}