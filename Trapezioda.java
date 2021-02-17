/**
* JAVA CODE FOR TRAPEZIODA METTHOD INTEGRAL NUMERIC
* UNIFORM DATA
* BY WARTEGDUST 
*/

public class Trapezioda{
	public float[] x;
	public float[] y;
	public float acceptableError = 0.0000001f;
	public float distance;

	public Trapezioda(float[] x, float[] y) {
		this.x = x;
		this.y = y;
	}


	public Trapezioda(float[] x, float[] y, float acceptableError) throws Exception{
		this.x = x;
		this.y = y;

		if (acceptableError < 1.0e-7f ) {
			throw new Exception("value acceptableError " + acceptableError + " is more little than float persistence value");
		}

		this.acceptableError = acceptableError;
	}

	public float estimateArea() throws Exception {
		// count 
		float area = 0.0f;
		if (this.isDataValid()) {
			float[] arrayX = this.x;
			float[] arrayY = this.y;

			float distance = arrayX[1] - arrayX[0];
			float sumY = 0.0f;
			for (float dataY : arrayY) {
				sumY += dataY;
			}

			this.distance = distance;

			area = (distance / 2) * (arrayY[0] + (2 * (sumY - arrayY[0] - arrayY[arrayY.length - 1] )) + arrayY[arrayY.length - 1] );

			return area;

		} else {
			throw new Exception("selisih data pada koordinate x tidak seragam");
		}
	}

	
	public boolean isDataValid() throws Exception {
		// function to check is x data valid
					float initDistance = x[1] - x[0];
			for (int idx = 1; idx < x.length - 1; idx++) {
				float nextVar = x[idx + 1];
				float befVar = x[idx];
				float curDistance = (nextVar - befVar) - initDistance;
				if (curDistance > acceptableError) {
					return false;
				}
			}

			return true;

		
	}

	public static void main(String[] args) {
		// FOR THEORY CHECK :: https://sufyan97.blogspot.com/2018/06/program-integral-numerik-metode.html

		float[] dataX = {0.0f, 0.2f, 0.4f, 0.6f, 0.8f, 1.0f};
		float[] dataY = {2.0f, 3.0f, 6.0f, 5.0f, 4.0f, 1.0f};

		try {
		Trapezioda trpzd = new Trapezioda(dataX, dataY, 1.0E-7f);
		System.out.println("luas area prediksi adalah : " + trpzd.estimateArea());
		System.out.println("dengan distance : " + trpzd.distance);
		} catch (Exception e) {
			System.out.println("error : " + e.toString());
		} 
	}
}