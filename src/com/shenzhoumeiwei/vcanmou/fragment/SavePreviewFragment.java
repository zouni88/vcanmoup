package com.shenzhoumeiwei.vcanmou.fragment;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.shenzhoumeiwei.vcanmou.R;
import com.shenzhoumeiwei.vcanmou.activity.BaseActivity;
import com.shenzhoumeiwei.vcanmou.adapter.ViewGroupExampleAdapter;
import com.shenzhoumeiwei.vcanmou.animoto.views.DraggableGridView_h;
import com.shenzhoumeiwei.vcanmou.animoto.views.OnRearrangeListener;
import com.shenzhoumeiwei.vcanmou.fancycoverflow.FancyCoverFlow;

public class SavePreviewFragment extends Fragment {
	private final String TAG = "SavePreviewFragment";
	
	private Context context = getActivity();
    private View root;
    static Random random = new Random();
    static String[] words = "the of and a to in is be that was he for it with as his I on have at by not they this had are but from or she an which you one we all were her would there their will when who him been has more if no out do so can what up said about other into than its time only could new them man some these then two first may any like now my such make over our even most me state after also made many did must before back see through way where get much go well your know should down work year because come people just say each those take day good how long Mr own too little use US very great still men here life both between old under last never place same another think house while high right might came off find states since used give against three himself look few general hand school part small American home during number again Mrs around thought went without however govern don't does got public United point end become head once course fact upon need system set every war put form water took".split(" ");
    DraggableGridView_h dgv;
    ArrayList<String> poem = new ArrayList<String>();
    
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.fragment_save_preview, container, false);
		initView();
		initData();
		return root;
	}
	
	private void initView(){
		dgv = ((DraggableGridView_h) root.findViewById(R.id.vgv));
		//指定DraggableGridView_h的高度
        dgv = (DraggableGridView_h) root.findViewById(R.id.vgv);
		int scW = 720;
        int childSize = (scW - 0) / 7;
        childSize = (int) Math.round(childSize * 0.9);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, childSize + 10* 2);
		//params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		dgv.setLayoutParams(params);
		
		FancyCoverFlow fancyCoverFlow = (FancyCoverFlow) root.findViewById(R.id.fancyCoverFlow);
//        fancyCoverFlow.setReflectionEnabled(false);
//        fancyCoverFlow.setReflectionRatio(0.1f);
//        fancyCoverFlow.setReflectionGap(10);

        fancyCoverFlow.setAdapter(new ViewGroupExampleAdapter());
	}
	
	private void initData(){
		dgv.setOnRearrangeListener(new OnRearrangeListener() {
			public void onRearrange(int oldIndex, int newIndex) {
				if(poem.size()>=1){
					String word = poem.remove(oldIndex);
					if (oldIndex < newIndex)
						poem.add(newIndex, word);
					else
						poem.add(newIndex, word);
				}
				
			}
		});
		/*String word = words[random.nextInt(words.length)];
		ImageView view = new ImageView(context);
		view.setImageBitmap(getThumb(word));
		*/
		LayoutInflater mInflater = ((BaseActivity)getActivity()).getLayoutInflater();
		dgv.addView(mInflater.inflate(R.layout.lay1, null));
		//poem.add(word);
	}
}
