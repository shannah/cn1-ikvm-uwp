using System;
using System.Collections.Generic;



#if !FIRST_PASS
using System.Threading.Tasks;
#endif
namespace IKVM.Runtime
{

    public class NativeThread
    {
#if !FIRST_PASS
        private Task peerTask;
        private static Dictionary<int,NativeThread> threads = new Dictionary<int,NativeThread>();
#endif

        private string _name;
        private bool _isBackground;
        private int _priority = NativeThreadPriority.Normal;


        public NativeThread(NativeThreadStart start)
        {
#if !FIRST_PASS
            peerTask = new Task(delegate() 
            {
                start.Invoke();
            }, new System.Threading.CancellationToken(), TaskCreationOptions.LongRunning);
            threads.Add(peerTask.Id, this);
#endif
        }

        public void Start()
        {
#if !FIRST_PASS
            peerTask.Start();
#endif
        }

        public static void Sleep(System.Int32 ms)
        {
#if !FIRST_PASS
            Task t = Task.Delay(ms);
            t.Wait();
#endif
        }

        public bool Join(int millisecondsTimeout)
        {
#if !FIRST_PASS
            return peerTask.Wait(millisecondsTimeout);
#else
            return false;
#endif
        }

        public void Join()
        {
#if !FIRST_PASS
            peerTask.Wait();
#endif
        }

        public string Name
        {
            get { return _name; }
            set { _name = value; }
        }

        public bool IsBackground
        {
            get { return _isBackground; }
            set { _isBackground = value; }
        }

        public int Priority
        {
            get { return _priority; }
            set { _priority = value; }
        }

        public static NativeThread CurrentThread
        {
            get
            {
#if !FIRST_PASS
                NativeThread nt;
                if (threads.TryGetValue(Task.CurrentId != null ? (int)Task.CurrentId : -1, out nt))
                {
                    return nt;
                }
                return null;
#else
                return null;
#endif
            }
        }

        public int ThreadState
        {
            get
            {
#if !FIRST_PASS
                switch (peerTask.Status)
                {
                    case TaskStatus.Canceled:
                    case TaskStatus.Faulted:
                    case TaskStatus.RanToCompletion:
                        return NativeThreadState.Stopped;
                    case TaskStatus.Created:
                    case TaskStatus.WaitingForActivation:
                    case TaskStatus.WaitingToRun:
                        return NativeThreadState.Unstarted;
                    case TaskStatus.Running:
                        return NativeThreadState.Running;
                    case TaskStatus.WaitingForChildrenToComplete:
                        return NativeThreadState.WaitSleepJoin;
                    


                }
#endif
                throw new NotImplementedException(); ;
            }
        }


    }



    public static class NativeThreadPriority
    {
        public const int AboveNormal = 0;
        public const int BelowNormal = 1;
        public const int Highest = 2;
        public const int Lowest = 3;
        public const int Normal = 4;
    }

    public static class NativeThreadState
    {
        public const int Aborted = 1;
        public const int AbortRequested = 2;
        public const int Background = 4;
        public const int Running = 8;
        public const int Stopped = 16;
        public const int StopRequested = 32;
        public const int Suspended = 64;
        public const int SuspendRequested = 128;
        public const int Unstarted = 256;
        public const int WaitSleepJoin = 512;
    }


    public interface NativeThreadStart
    {
        void Invoke();
    }
    public interface NativeWaitCallback
    {
        void Invoke();
    }

    public interface NativeWaitCallbackWithArg
    {
        void Invoke(object arg);
    }


    public class NativeThreadPool
    {
        public static bool QueueUserWorkItem(NativeWaitCallback callback)
        {
#if !FIRST_PASS
            Task.Run(delegate()
            {
                callback.Invoke();
            });
            
#endif
            return true;
        }

        public static bool QueueUserWorkItem(NativeWaitCallbackWithArg callback, object obj)
        {
#if !FIRST_PASS
            Task.Run(delegate()
            {
                callback.Invoke(obj);
            }
            );
#endif
            return true;
        }
    }
}