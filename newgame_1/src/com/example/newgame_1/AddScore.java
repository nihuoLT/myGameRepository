package com.example.newgame_1;




/**
 * Created by Administrator on 2017-02-20.
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
public class AddScore implements GameInterFace {
    public Bitmap score;
    public GameView gameView;
    public int x;
    public int y;
    public int startY,model;
    public AddScore(int x,int y,int model,GameView gameView){
        this.gameView=gameView;
        this.x=x;
        this.y=y;
        this.startY=y;
        this.model=model;
        switch (model) {
		case 1:
			score=BitmapFactory.decodeStream(gameView.getResources()
					.openRawResource(R.drawable.five));
			break;
		case 2:
			score=BitmapFactory.decodeStream(gameView.getResources()
					.openRawResource(R.drawable.three));
			break;
		case 3:
			score=BitmapFactory.decodeStream(gameView.getResources()
					.openRawResource(R.drawable.three));
			break;
		case 4:
			score=BitmapFactory.decodeStream(gameView.getResources()
					.openRawResource(R.drawable.four));
			break;
		}
    }

    @Override
    public Bitmap getBitmap() {
        y-=10;
        if(y<=startY-150){
//        	if(score!=null)
//        	score.recycle();
        	gameView.mys.remove(this);
            System.gc();
        }
        return score;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
