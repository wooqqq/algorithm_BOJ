import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        PriorityQueue<Meeting> meetings = new PriorityQueue<>(new Comparator<Meeting>() {
            @Override
            public int compare(Meeting m1, Meeting m2) {
                if (m1.start == m2.start) {
                    return m1.end - m2.end;
                }
                return m1.start - m2.start;
            }
        });

        PriorityQueue<Meeting> ongoingMeetings = new PriorityQueue<>(new Comparator<Meeting>() {
            @Override
            public int compare(Meeting m1, Meeting m2) {
                return m1.end - m2.end;
            }
        });

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetings.add(new Meeting(start, end));
        }


        ongoingMeetings.add(meetings.poll());

        while (!meetings.isEmpty()) {
            Meeting nextMeeting = meetings.poll();

            if (ongoingMeetings.isEmpty()) {
                ongoingMeetings.add(nextMeeting);
            }

            else if (nextMeeting.start < ongoingMeetings.peek().end) {
                ongoingMeetings.add(nextMeeting);
            }

            else if (nextMeeting.start >= ongoingMeetings.peek().end) {
                ongoingMeetings.poll();
                ongoingMeetings.add(nextMeeting);
            }

        }

        System.out.println(ongoingMeetings.size());

    }

    public static class Meeting {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}