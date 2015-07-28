package com.newloong.writing.paint;

import android.graphics.Path;


public interface Shapable {
	public Path getPath();

	public FirstCurrentPosition getFirstLastPoint();

	void setShap(ShapesInterface shape);
}
