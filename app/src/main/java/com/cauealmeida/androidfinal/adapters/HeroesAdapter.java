package com.cauealmeida.androidfinal.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cauealmeida.androidfinal.R;
import com.cauealmeida.androidfinal.model.SuperHero;

import java.util.List;

/**
 * Created by cauealmeida on 3/18/17.
 */
public class HeroesAdapter extends RecyclerView.Adapter<HeroesAdapter.HeroViewHolder> {
    private List<SuperHero> heroes;

    public HeroesAdapter(List<SuperHero> heroes) {
        this.heroes = heroes;
    }

    @Override
    public HeroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hero, parent, false);

        return new HeroViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HeroViewHolder holder, int position) {
        SuperHero hero = heroes.get(position);
        holder.tName.setText(hero.getName());
        holder.tBrand.setText(hero.getBrand());
    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }

    public class HeroViewHolder extends RecyclerView.ViewHolder {

        public TextView tName;
        public TextView tBrand;

        public HeroViewHolder(View itemView) {
            super(itemView);
            tName = (TextView) itemView.findViewById(R.id.tvName);
            tBrand = (TextView) itemView.findViewById(R.id.tvBrand);

        }
    }
}
