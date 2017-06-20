package net.ypresto.androidtranscoder.format;

import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.util.Size;

/**
 * @author Ben Underwood
 */

class AndroidSelected16By9FormatStrategy implements MediaFormatStrategy {

    Size mSize;
    int mVideoBitrate;

    public AndroidSelected16By9FormatStrategy(
            RequiredDimensions.Dimensions16By9 dimensions,
            int videoBitrate) {
        mSize = getSize(dimensions);
        mVideoBitrate = videoBitrate;
    }

    @Override
    public MediaFormat createVideoOutputFormat(MediaFormat inputFormat) {
        MediaFormat format = MediaFormat
                .createVideoFormat("video/avc", mSize.getWidth(), mSize.getHeight());

        format.setInteger(MediaFormat.KEY_BIT_RATE, mVideoBitrate);
        format.setInteger(MediaFormat.KEY_FRAME_RATE, 30);
        format.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, 3);
        format.setInteger(MediaFormat.KEY_COLOR_FORMAT,
                MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface);

        return format;
    }

    @Override
    public MediaFormat createAudioOutputFormat(MediaFormat inputFormat) {
        // No formatting to do on the audio.
        return null;
    }

    private Size getSize(RequiredDimensions.Dimensions16By9 dimensions) {
        Size size = null;

        switch (dimensions) {
            case dim640x360:
                size = new Size(640, 360);
                break;

            case dim1280x720:
                size = new Size(1280, 720);
                break;

            case dim1920x1080:
                size = new Size(1920, 1080);
                break;

            default:
                size = new Size(640, 360);
        }

        return size;
    }
}
