package advent_of_code.main.a2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import advent_of_code.main.utils.LectureFichiersUtils;

public class Day7 {

	public static void main(String[] args) {
		System.out.println(solve71());
		System.out.println(solve72());

		System.out.println(solve73());

	}

	private static int solve73() {
		Map<String, List<String>> data=getData();
		for(String s:data.keySet()) {
			System.out.println(data.get(s));
			
			
		}
		return 0;
	}

	private static int solve72() {
		int res = 0;
		int niveau1 = 0;
		int niveau2 = 0;
		int niveau3 = 0;
		int niveau4 = 0;
		int niveau5 = 0;
		int niveau6 = 0;
		int niveau7 = 0;
		int niveau8 = 0;

		List<RuleBagColor> listeRules = getRulesBag();
		RuleBagColor regleinitiale = new RuleBagColor("a", null);
		for (RuleBagColor rule : listeRules) {
			if (rule.getColor().equals("shiny gold")) {
				regleinitiale = rule;
			}
		}
		Bag myBag = new Bag("shiny gold", new ArrayList<Bag>());
		myBag.addBagsFromRule(regleinitiale);

		for (Bag b : myBag.getContenue()) {
			RuleBagColor maRule = getRulesBag().stream().filter(x -> x.getColor().equals(b.getColor())).findFirst()
					.get();
			b.addBagsFromRule(maRule);
			for (Bag bb : b.getContenue()) {
				RuleBagColor maRule2 = getRulesBag().stream().filter(x -> x.getColor().equals(bb.getColor()))
						.findFirst().get();
				bb.addBagsFromRule(maRule2);
				for (Bag bbb : bb.getContenue()) {
					RuleBagColor maRule3 = getRulesBag().stream().filter(x -> x.getColor().equals(bbb.getColor()))
							.findFirst().get();
					bbb.addBagsFromRule(maRule3);
					for (Bag bbbb : bbb.getContenue()) {
						RuleBagColor maRule4 = getRulesBag().stream().filter(x -> x.getColor().equals(bbbb.getColor()))
								.findFirst().get();
						bbbb.addBagsFromRule(maRule4);
						for (Bag bbbbb : bbbb.getContenue()) {
							RuleBagColor maRule5 = getRulesBag().stream()
									.filter(x -> x.getColor().equals(bbbbb.getColor())).findFirst().get();
							bbbbb.addBagsFromRule(maRule5);
							for (Bag bbbbbb : bbbbb.getContenue()) {
								RuleBagColor maRule6 = getRulesBag().stream()
										.filter(x -> x.getColor().equals(bbbbbb.getColor())).findFirst().get();
								bbbbbb.addBagsFromRule(maRule6);
								for (Bag bbbbbbb : bbbbbb.getContenue()) {
									RuleBagColor maRule7 = getRulesBag().stream()
											.filter(x -> x.getColor().equals(bbbbbbb.getColor())).findFirst().get();
									bbbbbbb.addBagsFromRule(maRule7);

								}
							}
						}

					}

				}

			}
		}

		for (Bag b : myBag.getContenue()) {
			if (!b.isDernier()) {
				System.out.println("b " + b.getColor());
				res++;
				niveau1++;
				for (Bag bb : b.getContenue()) {
					if (!bb.isDernier()) {
						System.out.println("bb " + bb.getColor());
						res++;
						niveau2++;
						for (Bag bbb : bb.getContenue()) {
							if (!bbb.isDernier()) {
								System.out.println("bbb " + bbb.getColor());
								res++;
								niveau3++;
								for (Bag bbbb : bbb.getContenue()) {
									if (!bbbb.isDernier()) {
										niveau4++;
										res++;
										for (Bag bbbbb : bbbb.getContenue()) {
											if (!bbbbb.isDernier()) {
												res++;
												niveau5++;
												for (Bag bbbbbb : bbbbb.getContenue()) {
													if (!bbbbbb.isDernier()) {
														niveau6++;
														res++;
														for (Bag bbbbbbbb : bbbbbb.getContenue()) {
															if (!bbbbbbbb.isDernier()) {
																niveau7++;
																res++;
																for (Bag bbbbbbbbb : bbbbbbbb.getContenue()) {
																	if (!bbbbbbbbb.isDernier()) {
																		niveau8++;
																		res++;

																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		System.out.println("niveau1 :" + niveau1);
		System.out.println("niveau2 :" + niveau2);
		System.out.println("niveau3 :" + niveau3);
		System.out.println("niveau4 :" + niveau4);
		System.out.println("niveau5 :" + niveau5);
		System.out.println("niveau6 :" + niveau6);
		System.out.println("niveau7 :" + niveau7);
		System.out.println("niveau8 :" + niveau8);
		System.out.println("nombre de valise de niveau 1:" + myBag.getContenue().size());
		// 29756 too high
		return res;

	}

	private static int solve71() {
		List<RuleBagColor> listeRules = getRulesBag();
		List<String> lienDirect = new ArrayList<String>();
		List<String> lienIndirect = new ArrayList<String>();
		List<String> lienIndirect2 = new ArrayList<String>();
		List<String> lienIndirect3 = new ArrayList<String>();
		List<String> lienIndirect4 = new ArrayList<String>();
		List<String> lienIndirect5 = new ArrayList<String>();
		List<String> lienIndirect6 = new ArrayList<String>();
		List<String> lienIndirect7 = new ArrayList<String>();

		for (RuleBagColor rule : listeRules) {
			if (rule.getContenu().containsKey("shiny gold")) {
				lienDirect.add(rule.getColor());
			}

		}
		for (String lien : lienDirect) {
			for (RuleBagColor rule : listeRules) {
				if (rule.getContenu().containsKey(lien)) {
					lienIndirect.add(rule.getColor());
				}
			}
		}

		for (String lien : lienIndirect) {
			for (RuleBagColor rule : listeRules) {
				if (rule.getContenu().containsKey(lien)) {
					lienIndirect2.add(rule.getColor());
				}
			}
		}
		for (String lien : lienIndirect2) {
			for (RuleBagColor rule : listeRules) {
				if (rule.getContenu().containsKey(lien)) {
					lienIndirect3.add(rule.getColor());
				}
			}
		}
		for (String lien : lienIndirect3) {
			for (RuleBagColor rule : listeRules) {
				if (rule.getContenu().containsKey(lien)) {
					lienIndirect4.add(rule.getColor());
				}
			}
		}
		for (String lien : lienIndirect4) {
			for (RuleBagColor rule : listeRules) {
				if (rule.getContenu().containsKey(lien)) {
					lienIndirect5.add(rule.getColor());
				}
			}
		}
		for (String lien : lienIndirect5) {
			for (RuleBagColor rule : listeRules) {
				if (rule.getContenu().containsKey(lien)) {
					lienIndirect6.add(rule.getColor());
				}
			}
		}
		for (String lien : lienIndirect6) {
			for (RuleBagColor rule : listeRules) {
				if (rule.getContenu().containsKey(lien)) {
					lienIndirect7.add(rule.getColor());
				}
			}
		}

		List<String> tousLien = new ArrayList<String>();
		tousLien.addAll(lienDirect);
		tousLien.addAll(lienIndirect);
		tousLien.addAll(lienIndirect2);
		tousLien.addAll(lienIndirect3);
		tousLien.addAll(lienIndirect4);
		tousLien.addAll(lienIndirect5);
		tousLien.addAll(lienIndirect6);
		tousLien.addAll(lienIndirect7);

		return (int) (tousLien.stream().distinct().count());
	}

	
	private static Map<String, List<String>> getData() {
		return LectureFichiersUtils.getData("input7").map(s -> s.replaceAll("\\sbag(s)?(\\.)?", ""))
				.map(s -> s.split("\\scontain\\s"))
				.collect(Collectors.toMap(s -> s[0], s-> Arrays.asList(s[1].split(",\\s"))));
	}
	
	
	private static List<RuleBagColor> getRulesBag() {
		Path path = Paths.get("C:\\AOC\\input7");
		FileReader fileReader;

		List<RuleBagColor> listeRules = new ArrayList<>();
		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);
			try {
				while (bufferReader.ready()) {
					String line = bufferReader.readLine();
					List<String> mots = Arrays.asList(line.split(" ")).subList(0, line.split(" ").length);
					if (mots.get(4).equals("no")) {
						listeRules.add(new RuleBagColor("" + mots.get(0) + " " + mots.get(1), new HashMap<>()));
					} else {
						String color = "" + mots.get(0) + " " + mots.get(1);
						Integer nbInfos = (mots.size() - 7) / 4 + 1;
						Map<String, Integer> contenu = new HashMap<>();
						int w = 1;
						while (w <= nbInfos) {
							for (int j = 4; j < mots.size() - 1; j = j + 4) {
								contenu.put("" + mots.get(j + 1) + " " + mots.get(j + 2),
										Integer.parseInt(mots.get(j)));
							}
							w++;
						}
						listeRules.add(new RuleBagColor(color, contenu));
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listeRules;
	}
}
