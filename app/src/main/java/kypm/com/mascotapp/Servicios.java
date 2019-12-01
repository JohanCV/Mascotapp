package kypm.com.mascotapp;

import java.util.List;

import kypm.com.mascotapp.modelo.Publicacion;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Servicios {
    @GET("servicios/canchas/")
    Call<List<Publicacion>> listarFotos();

    @Multipart
    @POST("/servicios/foto_mascota/")
    Call<ResponseBody> subirFoto(@Part MultipartBody.Part image, @Part("mascota") RequestBody mascota);
}
