package study.spring.model;

public class WeatherBeans {
	private int baseDate;
	private String baseTime;
	private String category;
	private int fcstDate;
	private int fcstTime;
	private long fcstValue;
	private int nx;
	private int ny;

	public int getBaseDate() {
		return baseDate;
	}

	public void setBaseDate(int baseDate) {
		this.baseDate = baseDate;
	}

	public String getBaseTime() {
		return baseTime;
	}

	public void setBaseTime(String baseTime) {
		this.baseTime = baseTime;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getFcstDate() {
		return fcstDate;
	}

	public void setFcstDate(int fcstDate) {
		this.fcstDate = fcstDate;
	}

	public int getFcstTime() {
		return fcstTime;
	}

	public void setFcstTime(int fcstTime) {
		this.fcstTime = fcstTime;
	}

	public long getFcstValue() {
		return fcstValue;
	}

	public void setFcstValue(long fcstValue) {
		this.fcstValue = fcstValue;
	}

	public int getNx() {
		return nx;
	}

	public void setNx(int nx) {
		this.nx = nx;
	}

	public int getNy() {
		return ny;
	}

	public void setNy(int ny) {
		this.ny = ny;
	}

	@Override
	public String toString() {
		return "WeatherBeans [baseDate=" + baseDate + ", baseTime=" + baseTime + ", category=" + category
				+ ", fcstDate=" + fcstDate + ", fcstTime=" + fcstTime + ", fcstValue=" + fcstValue + ", nx=" + nx
				+ ", ny=" + ny + "]";
	}

}
