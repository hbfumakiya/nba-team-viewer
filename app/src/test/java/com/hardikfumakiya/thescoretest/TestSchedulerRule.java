package com.hardikfumakiya.thescoretest;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.concurrent.Callable;

public class TestSchedulerRule implements TestRule {

    private final Scheduler immediate = new Scheduler() {
        @Override public Worker createWorker() {
            return new ExecutorScheduler.ExecutorWorker(Runnable::run);
        }
    };
    private final TestScheduler testScheduler = new TestScheduler();

    public TestScheduler getTestScheduler() {
        return testScheduler;
    }

    @Override
    public Statement apply(final Statement base, Description d) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxJavaPlugins.setIoSchedulerHandler(
                        new Function<Scheduler, Scheduler>() {
                            @Override
                            public Scheduler apply(Scheduler scheduler) throws Exception {
                                return Schedulers.trampoline();
                            }
                        });
                RxJavaPlugins.setComputationSchedulerHandler(
                        new Function<Scheduler, Scheduler>() {
                            @Override
                            public Scheduler apply(Scheduler scheduler) throws Exception {
                                return Schedulers.trampoline();
                            }
                        });
                RxJavaPlugins.setNewThreadSchedulerHandler(
                        new Function<Scheduler, Scheduler>() {
                            @Override
                            public Scheduler apply(Scheduler scheduler) throws Exception {
                                return Schedulers.trampoline();
                            }
                        });
                RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                        new Function<Callable<Scheduler>, Scheduler>() {
                            @Override
                            public Scheduler apply(Callable<Scheduler> scheduler) throws Exception {
                                return Schedulers.trampoline();
                            }
                        });

                try {
                    base.evaluate();
                } finally {
                    RxJavaPlugins.reset();
                    RxAndroidPlugins.reset();
                }
            }
        };
    }
}