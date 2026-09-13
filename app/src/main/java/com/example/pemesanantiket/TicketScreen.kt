package com.example.pemesanantiket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale

private val Biru = Color(0xFF2E90FA)
private val Hijau = Color(0xFF12B76A)
private val Merah = Color(0xFFF04438)
private val TeksGelap = Color(0xFF1D2939)
private val AbuMuda = Color(0xFFF7F8FA)

private const val HARGA_PER_TIKET = 25000

@Composable
fun TicketScreen() {
    var jumlahTiket by remember { mutableStateOf(1) }
    val totalBayar = HARGA_PER_TIKET * jumlahTiket

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Biru)
    ) {
        // HEADER
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 56.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.ConfirmationNumber,
                contentDescription = "Ikon Tiket",
                tint = Color.White,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Pemesanan Tiket",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Pesan tiket dengan mudah!",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 15.sp
            )
        }

        // CONTAINER BESAR ABU
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.5f)
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(AbuMuda)
                .padding(horizontal = 20.dp, vertical = 28.dp)
        ) {

            // HARGA TIKET
            InfoCard {
                Text(text = "Harga Tiket", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = TeksGelap)
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = formatRupiah(HARGA_PER_TIKET),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Biru
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "per tiket", fontSize = 14.sp, color = Color.Gray, fontWeight = FontWeight.SemiBold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // JUMLAH TIKET
            InfoCard {
                Text(
                    text = "Jumlah Tiket",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TeksGelap
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BulatIconButton(
                        icon = Icons.Default.Remove,
                        onClick = {
                            if (jumlahTiket > 1) jumlahTiket--
                        }
                    )

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .background(AbuMuda)
                            .padding(horizontal = 56.dp, vertical = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$jumlahTiket",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = TeksGelap
                        )
                    }

                    BulatIconButton(
                        icon = Icons.Default.Add,
                        onClick = { jumlahTiket++ }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // TOTAL
            InfoCard {
                Text(text = "Total", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = TeksGelap)
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = formatRupiah(totalBayar),
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    color = Hijau
                )
            }

            // tombol Reset ke paling bawah container
            Spacer(modifier = Modifier.weight(1f))

            // TOMBOL RESET
            Button(
                onClick = { jumlahTiket = 1 },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Merah)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Reset",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "RESET",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun InfoCard(content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            content = content
        )
    }
}

@Composable
fun BulatIconButton(icon: ImageVector, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(Biru)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White
        )
    }
}

private fun formatRupiah(jumlah: Int): String {
    val format = NumberFormat.getNumberInstance(Locale("in", "ID"))
    return "Rp${format.format(jumlah)}"
}

@Preview(showBackground = true)
@Composable
fun PreviewTicketScreen() {
    TicketScreen()
}