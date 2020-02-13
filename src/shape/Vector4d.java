package shape;

public class Vector4d {

    public float x, y, z, w;

    public Vector4d(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4d sub(float x, float y, float z, float w) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        this.w -= w;
        return this;
    }

    public Vector4d div(float x, float y, float z, float w) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
        this.w /= w;
        return this;
    }

    public Vector4d div(float n) {
        this.x /= n;
        this.y /= n;
        this.z /= n;
        this.w /= n;
        return this;
    }

    public Vector4d mult(float n) {
        this.x *= n;
        this.y *= n;
        this.z *= n;
        this.w *= n;
        return this;
    }

    public void setMag(float n) {
        this.norm();
        this.mult(n);
    }

    public void norm() {
        this.div(this.mag());
    }

    public float mag() {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w);
    }

    public float magSq() {
        return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
    }

    public void limit(float n) {
        if (this.magSq() > n * n * n * n) {
            this.div((float) Math.sqrt(this.magSq())).mult(n);
        }
    }
}
