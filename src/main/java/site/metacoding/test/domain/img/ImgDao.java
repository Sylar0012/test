package site.metacoding.test.domain.img;


public interface ImgDao {
	public void save(Img img);
	public void update();
	public Img findById(Integer id);

}
