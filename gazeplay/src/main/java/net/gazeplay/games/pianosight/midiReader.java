package net.gazeplay.games.pianosight;

import java.io.IOException;
import java.io.InputStream;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class midiReader {
    public static final int NOTE_ON = 0x90;
    public static final int NOTE_OFF = 0x80;
    public static final String[] NOTE_NAMES = { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" };
    Track track;
    Instru instru;
    long prevTick;
    int key;
    int tickIndex;

    public midiReader(InputStream inputStream) {
        instru = new Instru();
        try {
            Sequence sequence = MidiSystem.getSequence(inputStream);
            int maxIndex = 0;
            int max = 0;
            for (int i = 0; i < sequence.getTracks().length; i++) {
                if (max < sequence.getTracks()[i].size()) {
                    maxIndex = i;
                }
            }
            track = sequence.getTracks()[maxIndex];
            tickIndex = -1;
            prevTick = -1;
            key = -1;
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int nextNote() {
        int note = -1;
        if (tickIndex + 1 < track.size()) {
            tickIndex++;
            MidiEvent event = track.get(tickIndex);
            long tick = event.getTick();
            MidiMessage message = event.getMessage();
            if (message instanceof ShortMessage) {
                ShortMessage sm = (ShortMessage) message;
                if (sm.getCommand() == NOTE_ON) {
                    int velocity = sm.getData2();
                    if ((sm.getChannel() == 0) && (prevTick != event.getTick())) {
                        prevTick = event.getTick();
                        key = sm.getData1();
                        note = key % 12;
                        String noteName = NOTE_NAMES[note];
                        return note;
                    } else {
                        return nextNote();
                    }
                } else {
                    return nextNote();
                }
            } else {
                return nextNote();
            }
        }
        return note;
    }
}