package com.kvlt.concurrency;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 04、Actor模型
 *      一切看做一个actor——计算实体
 *      应答消息时，可以给其他actor发送消息，或者创建新的actor并与之交互，或者只改变自己的内部状态
 *      actor模型强调imiao全局状态。
 * @author daishengkai
 * 2018-05-13 20:08
 */
public class C4Querier extends UntypedAbstractActor {

    private String question;
    private List<String> engines;
    private AtomicReference<String> result;

    public C4Querier(String question, List<String> engines, AtomicReference<String> result) {
        this.question = question;
        this.engines = engines;
        this.result = result;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof C4Message) {
            result.compareAndSet(null, ((C4Result) message).html);
            getContext().stop(self());
        } else {
            for (String base : engines) {
                String url = base + question;
                ActorRef fetcher = this.getContext().actorOf(Props.create(C4UrlFetcher.class), "fetcher-" + base.hashCode());
                C4Message mess = new C4Message(url);
                fetcher.tell(mess, self());
            }
        }
    }

    private static String getFirstResultActors(String question, List<String> engines) {
        ActorSystem system = ActorSystem.create("Search");
        AtomicReference<String> result = new AtomicReference<>();

        final ActorRef q = system.actorOf(Props.create(() -> new C4Querier(question, engines, result)), "master");
        q.tell(new Object(), ActorRef.noSender());

        while (result.get() == null) ;
        return result.get();
    }

    public static void main(String[] args) {
        /*ActorSystem system = ActorSystem.create("getFirstResult");
        final ActorRef toString = system.actorOf(Props.create(C4Querier.class), "first");
        for (int i=0; i<100; i++) {
            toString.tell("test" + i, toString);
        }*/
        String s = getFirstResultActors("Tell me Why?", Lists.newArrayList(new String[]{"e1", "e2", "e3"}));
        System.out.println(" ----> " + s);
        System.out.println(" --------- 结束 ---------- ");
    }
}
