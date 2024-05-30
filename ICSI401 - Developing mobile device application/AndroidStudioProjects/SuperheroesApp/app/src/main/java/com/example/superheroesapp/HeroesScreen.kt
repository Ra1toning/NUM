package com.example.superheroesapp


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.superheroesapp.model.Hero
import com.example.superheroesapp.model.HeroesRepository.heroes

@Composable
fun HeroList(
    heroes: List<Hero>,
    modifier: Modifier = Modifier
)
{
    LazyColumn(modifier = modifier) {
        items(heroes) {
            HeroListItem(hero = it,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)))
        }
    }
}
@Composable
fun HeroListItem(hero: Hero,
                 modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(id = R.dimen.elevation)),
        modifier = modifier.clip(MaterialTheme.shapes.medium)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .sizeIn(minHeight = 72.dp)
            .padding(16.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = stringResource(hero.nameRes),
                    style = MaterialTheme.typography.displaySmall)
                Text(text = stringResource(hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_medium)))

            Box(modifier = Modifier
                .size(dimensionResource(id = R.dimen.image_size))
                .clip(MaterialTheme.shapes.small)
                )
            {
                Image(painter = painterResource(hero.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.TopCenter
                )
            }
        }
    }
}

