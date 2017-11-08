package com.bwie.springview;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;
/**
 * SpringView有哪些优点？

    1. 能对header/footer（头部/尾部）的样式和动画进行高度自定义，单独将header/footer独立出来，几乎可以实现任何你想要的效果，只需要继承BaseHeader/BaseFooter实现对应接口就可以。

    2. 能动态地替换header/footer，只需要设置不同的头尾即可：springView.setHeader(MyHeader());

    3. 在不重写源生控件的情况下，完美支持系统源生的listView、RecyclerView、ScrollView、WebView等，你依然使用google提供的官方控件，SpringView完全不做干涩。

    4. 使用简单，对于简单的需求甚至不用写任何代码，只需要在布局中为SpringView设置header=”@layout/…”属性即可。

    5. SpringView是非常轻量级的实现，提供了非常容易拓展的对外接口

    6. SpringView支持多点触控，可以两只手连续拖拽，你可以定制一些趣味的动画（例如demo5的仿acfun效果）

    7. SpringView提供了2种拖拽方式（重叠和跟随），可以动态地切换

    8. SpringView为不想去自定义头/尾的懒人提供了7种默认的实现（模仿了阿里，腾讯，美团等多种风格）如下，还会继续增加
 */
public class MainActivity extends Activity {
    private SpringView springView;
    private RecyclerView recyclerView;
    private List<String> list = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        list.add("http://img1.imgtn.bdimg.com/it/u=2710025438,2030209695&fm=27&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=2749800578,1172713594&fm=27&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=1364748988,3595630243&fm=27&gp=0.jpg");
        list.add("http://img2.imgtn.bdimg.com/it/u=1313667106,1509125836&fm=27&gp=0.jpg");
        list.add("http://f10.baidu.com/it/u=2881303562,336932824&fm=72");
        list.add("http://f11.baidu.com/it/u=681755471,2018070071&fm=72");
        list.add("http://f10.baidu.com/it/u=960650584,863938083&fm=72");
        list.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3437523560,2570312276&fm=27&gp=0.jpg");
        list.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1048304009,1533562216&fm=27&gp=0.jpg");
        list.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3744146906,172628736&fm=27&gp=0.jpg");
        list.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=900946121,4058384925&fm=27&gp=0.jpg");
        list.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2746401840,2463337952&fm=27&gp=0.jpg");
        list.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=4009572336,3320118404&fm=27&gp=0.jpg");
        list.add("http://f10.baidu.com/it/u=2881303562,336932824&fm=72");
        list.add("http://f11.baidu.com/it/u=681755471,2018070071&fm=72");
        list.add("http://f10.baidu.com/it/u=960650584,863938083&fm=72");

        //设置布局管理器
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        //设置适配器
        MultiAdapter adapter =  new MultiAdapter(this,list) ;
        recyclerView.setAdapter(adapter);

        springView = (SpringView) findViewById(R.id.springview);

        //DefaultHeader/Footer是SpringView已经实现的默认头/尾之一
        //更多还有MeituanHeader、AliHeader、RotationHeader等
        springView.setHeader(new DefaultHeader(this));
        springView.setFooter(new DefaultFooter(this));

        //springView的监听事件
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //下拉加载
                springView.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                // 上拉刷新
                springView.onFinishFreshAndLoad();
            }
        });
    }
}
