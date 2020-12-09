package com.ahui.test;

import com.ahui.domain.Blog;
import com.ahui.service.IBlogService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class TestBlog {
    @Test
    public void testInsert(){
        Blog blog = new Blog();
        blog.setBlogUserID(1);
        blog.setBlogReleaseDate(new Date());
        blog.setBlogTitle("像我这样的人");
        String blogContent = "“我是谁”时常问起自己，并非一个名字，全世界七十四亿人口，这名字不单单仅我一人，借用吕秀才的话来讲名字只是一个代号。 那我到底是谁——我是一个 一年365天活出365个目标，具有独特思维头脑的生命体。\n" +
                "我为数不多的优点可能就是遇大事能够沉着稳定的，遇到许许多多的事令我崩溃的往往不是什么天大的事，反而通常是一些生活中的小事。或许这便是压死骆驼的最后一根租草，一些大事的发生反而我加更加冷静。亲人重病卧床不会一味的哭泣;分手失恋不会一度的颓废懊悔:朋友误解不会刻意去解释，迎合.总一笑而过。\n" +
                "因为20余岁的我已经明白一些道理:永远不要让情缘左右自己理智，但渐渐地也发现自己总是自我才盾的缺点，矛盾已经到了可以和父亲举酒碰杯的年纪，可没有了和父亲小时的亲近愉悦;自己到了可以和长辈促膝长谈的年纪，却处处觉得长辈言语中充满讽刺，到了可以直言不讳说爱的年轮，都没有了曾经年少时纯真的喜欢!现在到了自己小时候渴望的年龄，现在又向往儿时的欢乐。\n" +
                "感觉自己又是一个成熟过头的人，才20来岁的我已收藏了多幅面具，在父亲长辈的圈子里明白要带上一个乖宝宝的面具，可谁知道自己在外面活的一败涂地，让自己戴上玩世不恭的面具，可只有自己知道真的很讨厌自己浑浑噩噩的样子，在亲朋好友的圈子里，让自己带上积极进取的面具可他们真不知道我有时真的累了。\n" +
                "只有一个人的时候喜欢坐在个角落里带上耳机，听听民谣歌曲，哆哈大笑，号啕大哭，谁都不知道，也不想让别人知道，只有自己明白在所有人面前是故作坚强，自己在世人面前无味， 无谓，无畏。\n";
        blog.setBlogContent(blogContent);

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        IBlogService blogService = (IBlogService) applicationContext.getBean("blogService");
        blogService.insertBlog(blog);
    }
}
