package cn.uyiku.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.uyiku.mapper.ClothMapper;
import cn.uyiku.mapper.PlanClothMapper;
import cn.uyiku.pojo.Cloth;
@Service
public class ClothServiceImpl implements ClothService {
	@Resource
	private ClothMapper clothMapper;
	@Resource
	private PlanClothMapper planClothMapper;
	// 这个地方是季节, 使用一个获取方法来讲当前的季节来转换成对应的季节
	
	//=============================
	/*
	 * 这个地方需要一个将季节转换成对应季节属性的方法
	 */
	String season = "春秋";
	


	@Override
	public List<Cloth> findClothList(String style,String userId) {
		
		List<Cloth> clothList = clothMapper.findAllCloth(userId);
		List<Cloth> clothListSeason = new ArrayList<Cloth>();
		//处理成季节的数组
		for (Cloth cloth : clothList) {
			if(cloth.getSeason().equals(season)){
				clothListSeason.add(cloth);
			}
		}
		return clothListSeason;
		
	}

	@Override
	public Cloth findOneClothById(String cId) {
		//下面的cid是测试数据
	
		return clothMapper.findOneClothById(cId);
	}

	@Override
	public void updateCloth(Cloth cloth) {
		cloth.setLastUpdateTime(new Date());
		clothMapper.updateCloth(cloth);
	}

	@Override
	public List<Cloth> findClothByTag(String style, String season, String category,String userId) {
		
		return clothMapper.findClothByTag(style,season,category,userId);
	}
	/*
	 * (non-Javadoc)
	 * @see cn.uyiku.service.ClothService#findClothListByPId(java.lang.String)
	 */
	@Override
	public List<Cloth> findClothListByPId(String Pid) {
		
		return clothMapper.findClothListByPId(Pid);
	}
	
	
	@Override
	public void deleteClothById(String[] id) {
		/*
		 * 因为表之间有外键关联，所以先删除关联表，后删除衣物表
		 */
	//	planClothMapper.deleteClothById(cId);//根据衣物id删除关联表plan_cloth表上的记录
		//clothMapper.deleteClothById(cId);//根据衣物id删除衣物表上的记录
		String[] ids = {"12"};
		clothMapper.deleteClothById(ids);//根据衣物id删除衣物表上的记录
	}
	@Override
	public List<Cloth> findLike(String word,String userId) {
		List<Cloth> findLike = clothMapper.findLike(word,userId);
		System.out.println("asdadasdasdas");
		for (Cloth cloth : findLike) {
			System.out.println(cloth);
		}
		return findLike;
	}
	@Override
	public void saveCloth(Cloth cloth,String userId) {
		/*
		 * 设置创建时间
		 */
		clothMapper.saveCloth(cloth,userId);
	}



	@Override
	public List<Cloth> findAllClothList(String userId) {
		return clothMapper.findAllClothList(userId);
		
	}


	
	
	
}
