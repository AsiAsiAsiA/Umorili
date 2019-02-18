package com.example.umoriliapi;

import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {
    private static final String TAG = PostsAdapter.class.getSimpleName();

    private List<PostModel> posts;

    PostsAdapter(List<PostModel> posts) {
        this.posts = posts;
    }


    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new PostViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.bind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        if (posts == null)
            return 0;
        return posts.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        TextView post;
        TextView site;

        PostViewHolder(View itemView) {
            super(itemView);
            post = itemView.findViewById(R.id.postitem_post);
            site = itemView.findViewById(R.id.postitem_site);
            itemView.setTag(itemView);
        }

        void bind(final PostModel postModel) {
            String source = postModel.getElementPureHtml();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                post.setText(Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY));
            } else {
                post.setText(Html.fromHtml(source));
            }
            site.setText(postModel.getSite());
        }
    }
}